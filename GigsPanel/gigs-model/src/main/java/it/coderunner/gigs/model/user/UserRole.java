package it.coderunner.gigs.model.user;

import lombok.Getter;

public enum UserRole {
	
	/**
	 * zwykły użytkownik
	 */
	ROLE_USER("ROLE_USER"), 
	
	ROLE_ADMIN("ROLE_ADMIN");
	
	private UserRole(String code){
		this.code=code;
	}

	/**
	 * Kod roli. Wykorzystywana głównie na potrzeby Spring Security. Format obowiązkowy: 'ROLE_XXX'.
	 */
	@Getter
	private String code;
	
}
