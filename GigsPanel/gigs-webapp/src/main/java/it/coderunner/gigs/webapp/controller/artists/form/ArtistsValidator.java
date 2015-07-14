package it.coderunner.gigs.webapp.controller.artists.form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

import it.coderunner.gigs.webapp.validator.CommonValidator;

public class ArtistsValidator extends CommonValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ArtistsForm.class);
	}

	@Override
	public void validateForm(Object target, Errors errors) {

		ArtistsForm form = (ArtistsForm) target;

		if (StringUtils.isBlank(form.getArtistName())) {
			errors.rejectValue("artist.name", "artist.name.cannot.be.null");
		}

		if (StringUtils.isBlank(form.getTag())) {
			errors.rejectValue("artist.tag", "artist.tag.cannot.be.null");
		}

	}
}
