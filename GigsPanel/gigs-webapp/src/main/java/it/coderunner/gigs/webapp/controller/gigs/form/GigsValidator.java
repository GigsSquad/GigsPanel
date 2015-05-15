package it.coderunner.gigs.webapp.controller.gigs.form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import it.coderunner.gigs.service.gigs.IGigService;
import it.coderunner.gigs.webapp.validator.CommonValidator;

public class GigsValidator extends CommonValidator {
	
	@Autowired
	private IGigService gigService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(GigsForm.class);
	}

	@Override
	public void validateForm(Object target, Errors errors) {
		
		GigsForm form = (GigsForm) target;
		
		if (StringUtils.isBlank(form.getGig().getArtist().getName())){
			errors.rejectValue("gig.artist.name", "gig.artist.cannot.be.null");
		}
		if (StringUtils.isBlank(form.getAuthor())){
			errors.rejectValue("author", "author.cannot.be.null");
		}
		
	}

}
