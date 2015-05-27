package it.coderunner.gigs.webapp.controller.register.form;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.webapp.mvc.AbstractForm;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class RegisterForm extends AbstractForm {

	private static final long serialVersionUID = -6531446778265375752L;

	@Getter
	@Setter
	private User user;

	@Override
	public String getID() {
		return new Md5PasswordEncoder().encodePassword(user.getId() + "", "sdfNOghELOjklkjuhLOGINygtfrLOLrtgyhjk");
	}

}
