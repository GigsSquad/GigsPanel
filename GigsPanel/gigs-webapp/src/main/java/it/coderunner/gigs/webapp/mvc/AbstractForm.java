package it.coderunner.gigs.webapp.mvc;

import java.io.Serializable;

/**
 * Klasa bazowa, po której mają dziedziczyć wszystkie formy wyrzucane do modelu
 * na widok (DTO)
 */
public abstract class AbstractForm implements Serializable {

	private static final long serialVersionUID = -40593254915371736L;

	/**
	 * Zwraca identyfikator formy. Identyfikacja form jest używana podczas
	 * przechowywania form w sesji jako atrybuty sesyjne. Jeżeli forma o tym
	 * samym ID jest ponownie ładowana do sesji, zastępuje ona obecną formę.
	 * Jeżeli jej ID jest różne, oba obiekty są przechowywane w sesji.
	 * 
	 * @return
	 */
	public abstract String getID();
}
