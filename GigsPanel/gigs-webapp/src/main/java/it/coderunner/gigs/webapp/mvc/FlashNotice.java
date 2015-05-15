package it.coderunner.gigs.webapp.mvc;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class FlashNotice implements Serializable{

	private static final long serialVersionUID = -7360568228615650845L;
	@Getter @Setter
	private String value;
	@Getter @Setter
	private Severity severity;
	
	public FlashNotice(String msg, Severity severity){
		this.value = msg;
		this.severity = severity;
	}
}
