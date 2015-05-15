package it.coderunner.gigs.webapp.mvc;

import java.io.Serializable;

import lombok.Getter;

public enum Severity implements Serializable {
	
	SUCCESS("success"), INFO("info"), WARNING("warning"), ERROR("danger");
	
	@Getter
	private String className;
	
	private Severity(String className){
		this.className=className;
	}	
}
