package it.coderunner.gigs.webapp.controller.artists.form;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.webapp.mvc.AbstractForm;
import lombok.Getter;
import lombok.Setter;

public class ArtistsForm extends AbstractForm{
	private static final long serialVersionUID = -1973042451107100163L;
	
	@Getter
	@Setter 
	Artist artist;

	@Override
	public String getID() {
		return new Md5PasswordEncoder().encodePassword(artist.getId()+"", "trodlsoldaoaskdoas");
	}

}
