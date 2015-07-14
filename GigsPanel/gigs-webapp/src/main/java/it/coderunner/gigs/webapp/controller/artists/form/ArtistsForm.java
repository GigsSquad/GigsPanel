package it.coderunner.gigs.webapp.controller.artists.form;

import it.coderunner.gigs.webapp.mvc.AbstractForm;
import lombok.Getter;
import lombok.Setter;

public class ArtistsForm extends AbstractForm{
	private static final long serialVersionUID = -1973042451107100163L;
	
	@Getter
	@Setter 
	String  artistName;
	
	@Getter
	@Setter
	String tag;

	@Override
	public String getID() {
		return null;
	}


}
