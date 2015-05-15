package it.coderunner.gigs.webapp.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

/**
 * Domyślny walidator dla templatek form.
 * @author andrzej
 *
 */
public abstract class CommonValidator implements Validator {


	protected Errors errors;
	
	/**
	 * Standardowy algorytm walidacji templatki - walidacja właściwa, oraz przypisanie błędów.
	 */
	public void validate(Object target, Errors errors) {
		validateForm(target, errors);		
		this.errors = errors;
	}

	/**
	 * Walidacja właściwa. Powinna zawierać logikę walidacji dla obiektu
	 * @param target - obiekt (formatka), który chcemy zwalidować
	 * @param errors2
	 */
	public abstract void validateForm(Object target, Errors errors);

	/**
	 * Zwraca ilość błędów, jakie wykrył walidator
	 * @return
	 */
	public int getErrorsCount() {
		return this.errors == null ? 0 : this.errors.getErrorCount();
	}

	/**
	 * Zwraca wszystkie błędy, jakie wykrył walidator (zarówno globalne, jak i przypisane do pól formatki).
	 * @return
	 */
	public List<ObjectError> getErrors() {
		return errors == null ? null : errors.getAllErrors();
	}

	/**
	 * sprawdza, czy walidator wykrył błędy
	 * @return
	 */
	public boolean hasErrors() {
		return errors.hasErrors();
	}

	/**
	 * Sprawdza, czy wśród wykrytych błędów przez walidator, znajduje się błąd o podanym kodzie
	 * @param messageCode - kod błędy, którego sprawdzamy
	 * @return
	 */	
	public boolean hasErrorCode(String messageCode) {
		if(StringUtils.isBlank(messageCode) || !hasErrors()){
			return false;
		}
		for(ObjectError objectError : getErrors()){
			if(messageCode.equals(objectError.getCode())){
				return true;
			}
		}
		return false;
	}	
	
	/**
	 * Wyświetla na konsolę wszystkie błędy, jakie zawiera walidator
	 */
	public void displayErrors(){
		if(hasErrors()){
			for(ObjectError oe : getErrors()){
				System.out.println(oe.getCode());
			}
		}
	}
}
