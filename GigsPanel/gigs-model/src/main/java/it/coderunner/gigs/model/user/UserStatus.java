package it.coderunner.gigs.model.user;


/**
 * Status zarejestrowanego w systemie użytkownika.
 * Zarejestrowany użytkownik może mieć tylko jeden status.
 *
 */
public enum UserStatus {

	/**
	 * oczekujący na aktywację
	 */
	PENDING, 
	/**
	 * aktywny
	 */
	ACTIVE, 
	/**
	 * zablokowany
	 */
	LOCKED
}
