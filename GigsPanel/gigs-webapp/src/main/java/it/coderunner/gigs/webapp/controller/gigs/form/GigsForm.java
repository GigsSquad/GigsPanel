package it.coderunner.gigs.webapp.controller.gigs.form;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.webapp.mvc.AbstractForm;
import lombok.Getter;
import lombok.Setter;

public class GigsForm extends AbstractForm{
	
	private static final long serialVersionUID = -3240705335050833616L;

	@Getter @Setter
	private Gig gig;
	
	@Getter @Setter
	private String author;
	
	@Override
	public String getID() {
		return new Md5PasswordEncoder().encodePassword(gig.getId()+"", "chujakupacubnscaadfadaswkj");
	}

}
