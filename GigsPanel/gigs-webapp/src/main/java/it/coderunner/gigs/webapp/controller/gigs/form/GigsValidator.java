package it.coderunner.gigs.webapp.controller.gigs.form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import it.coderunner.gigs.service.gigs.IGigService;
import it.coderunner.gigs.webapp.validator.CommonValidator;

public class GigsValidator extends CommonValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(GigsForm.class);
	}

	@Override
	public void validateForm(Object target, Errors errors) {

		GigsForm form = (GigsForm) target;

		if (StringUtils.isBlank(form.getGig().getArtist().getName())) {
			errors.rejectValue("gig.artist.name", "gig.artist.cannot.be.null");
		}

		if (StringUtils.isBlank(form.getGig().getSpot().getCity())) {
			errors.rejectValue("gig.spot.city", "gig.city.cannot.be.null");
		}

		if (StringUtils.isBlank(form.getGig().getSpot().getClub())) {
			errors.rejectValue("gig.spot.club", "gig.club.cannot.be.null");
		}

		// if (StringUtils.isBlank(form.getGig().getUrl())) {
		// errors.rejectValue("gig.url", "gig.url.cannot.be.null");
		// }
		//
		// if (form.getGig().getAgency() == null) {
		// errors.rejectValue("gig.agency", "gig.agency.cannot.be.null");
		// }
		//
		// if (form.getGig().getDate() == null) {
		// errors.rejectValue("gig.date", "gig.date.cannot.be.null");
		// }
		//
		// if (StringUtils.isBlank(form.getAuthor())) {
		// errors.rejectValue("author", "author.cannot.be.null");
		// }
	}
}
