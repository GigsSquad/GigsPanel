package it.coderunner.gigs.webapp.controller.spots.form;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import lombok.Getter;
import lombok.Setter;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.webapp.mvc.AbstractForm;

public class SpotForm extends AbstractForm{

	@Getter @Setter
	private Spot spot;
	
	@Getter @Setter
	private String author;
	
	@Override
	public String getID() {
		return new Md5PasswordEncoder().encodePassword(spot.getId()+"", "chujakupacubnscaadfadaswkj");
	}
	
}
