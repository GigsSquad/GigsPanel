package it.coderunner.gigs.model.user;

import java.util.Locale;

import lombok.Getter;

public enum UserLocale {
	en("English", "en"), pl("Polski", "pl");
	
	private UserLocale(String name, String shortName){
		this.originName = name;
		this.shortName = shortName;
		this.locale = new Locale(shortName);
	}
	@Getter
	private String shortName;
	
	@Getter
	private String originName;
	
	@Getter
	private Locale locale;
	
};