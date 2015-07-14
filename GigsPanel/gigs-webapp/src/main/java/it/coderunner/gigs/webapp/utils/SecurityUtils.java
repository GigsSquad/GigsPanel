package it.coderunner.gigs.webapp.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

	/**
	 * Sprawdza, czy na stronie znajduje się zalogowany, czy anonimowy
	 * użytkownik
	 * 
	 * @return
	 */
	public static boolean isUserLogged() {
		try {
			return !SecurityContextHolder.getContext().getAuthentication()
					.getName().equals("anonymousUser");
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Zwraca login zalogowanego użytkownika (bądź pusty String w przypadku
	 * braku zalogowania)
	 * 
	 * @return
	 */
	public static String getLoggedUsername() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			return SecurityContextHolder.getContext().getAuthentication()
					.getName();
		}
		return "";
	}
}
