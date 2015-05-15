package it.coderunner.gigs.webapp.mvc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class FlashErrors implements Serializable {

	private static final long serialVersionUID = -5230780590321176100L;
	public static final String ATTRIBUTE_NAME = "flash_errors";
	
	private Errors errors;
	
	public FlashErrors(Errors errors){
		this.errors=errors;
	}
	
	public Map<String, List<String>> generateFlashErrors(){
		if(errors==null || !errors.hasErrors() || errors.getErrorCount()==0){
			return null;
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for(ObjectError error : errors.getAllErrors()){
			List<String> errorsForObjectName = map.get(error.getObjectName());
			if(errorsForObjectName==null){
				errorsForObjectName = new ArrayList<String>();
			}
			errorsForObjectName.add(error.getDefaultMessage());
			map.put(error.getObjectName(), errorsForObjectName);
		}
		return map;
	}

	public void populate(RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute(ATTRIBUTE_NAME, generateFlashErrors());
	}
}
