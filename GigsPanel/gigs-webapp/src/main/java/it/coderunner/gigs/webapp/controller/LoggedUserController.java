package it.coderunner.gigs.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class LoggedUserController {
	@Autowired
	protected SessionPreferences sessionPreferences;

	@ModelAttribute("displayName")
	public String getDisplayName() {
		return sessionPreferences.getDisplayName();
	}

}
