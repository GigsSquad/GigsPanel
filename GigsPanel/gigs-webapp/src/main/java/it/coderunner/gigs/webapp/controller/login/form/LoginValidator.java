package it.coderunner.gigs.webapp.controller.login.form;

import it.coderunner.gigs.service.users.IUserService;
import it.coderunner.gigs.webapp.validator.CommonValidator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

public class LoginValidator extends CommonValidator {

	@Autowired
	private IUserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(LoginForm.class);
	}

	@Override
	public void validateForm(Object target, Errors errors) {

		LoginForm form = (LoginForm) target;

		if (StringUtils.isBlank(form.getUser().getUsername())) {
			errors.rejectValue("user.username", "user.username.cannot.be.null");
		}

		if (StringUtils.isBlank(form.getUser().getPassword())) {
			errors.rejectValue("user.password", "user.password.cannot.be.null");
		}
	}
}