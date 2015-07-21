package it.coderunner.gigs.webapp.controller.spots.form;

import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.webapp.mvc.AbstractForm;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class SpotsForm extends AbstractForm {

	private static final long serialVersionUID = -3240705335050833616L;

	@Getter
	@Setter
	private Spot spot;

	@Getter
	@Setter
	private String author;

	@Override
	public String getID() {
		return new Md5PasswordEncoder().encodePassword(spot.getId() + "",
				"chujakupacubnscaadfadaswkj");
	}

}
