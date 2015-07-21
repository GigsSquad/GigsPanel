package it.coderunner.gigs.webapp.controller.spots.form;

import it.coderunner.gigs.service.spots.ISpotService;
import it.coderunner.gigs.webapp.validator.CommonValidator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

public class SpotsValidator extends CommonValidator {

	@Autowired
	private ISpotService spotService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(SpotsForm.class);
	}

	@Override
	public void validateForm(Object target, Errors errors) {

		SpotsForm form = (SpotsForm) target;
		
		if (StringUtils.isBlank(form.getSpot().getCity())) {
			errors.rejectValue("spot.city", "spot.city.cannot.be.null");
			System.out.println("City null");
		}

		if (StringUtils.isBlank(form.getSpot().getAddress())) {
			errors.rejectValue("spot.address", "spot.address.cannot.be.null");
			System.out.println("Address null");
		}

		if (StringUtils.isBlank(form.getSpot().getClub())) {
			errors.rejectValue("spot.club", "spot.club.cannot.be.null");
			System.out.println("Club null");
		}

	}
}
