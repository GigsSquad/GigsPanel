package it.coderunner.gigs.webapp.mvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;

public class ModelAndViewUtils {

	/**
	 * Sprawdza, czy strona do której kierowany jest model nie jest redirectem
	 * 
	 * @param mv
	 * @return
	 */
	public static boolean isRedirectView(ModelAndView mv) {

		String viewName = mv.getViewName();
		if (viewName.startsWith("redirect:/")) {
			return true;
		}

		View view = mv.getView();
		return (view != null && view instanceof SmartView && ((SmartView) view).isRedirectView());
	}

	public static Map<String, Object> generateMessageMap(Severity severity, String message, Object... objects) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", message);
		map.put("severity", severity);
		return map;
	}

	public static void populateMessages(final BindingResult result, FlashMessages messages) {
		for (ObjectError error : result.getAllErrors()) {
			String message = error.getCode();
			messages.addMessage(message, Severity.ERROR);
		}
	}
}
