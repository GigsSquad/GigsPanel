package it.coderunner.gigs.webapp.controller.spots.form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import it.coderunner.gigs.service.spots.ISpotService;
import it.coderunner.gigs.webapp.controller.gigs.form.GigsForm;
import it.coderunner.gigs.webapp.validator.CommonValidator;

public class SpotValidator extends CommonValidator {

	@Autowired
	private ISpotService spotService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(GigsForm.class);
	}

	@Override
	public void validateForm(Object target, Errors errors) {

		SpotForm form = (SpotForm) target;
		
		if (StringUtils.isBlank(form.getSpot().getCity())){
			errors.rejectValue("spot.city", "spot.city.cannot.be.null");
		}
		
		if (StringUtils.isBlank(form.getSpot().getClub())){
			errors.rejectValue("spot.club", "spot.club.cannot.be.null");
		}
	}

}
