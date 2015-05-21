package it.coderunner.gigs.service.secure;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.service.IService;

public interface ISaltComponentService extends IService {

	/**
	 * Generuje hash aktywacyjny dla użytkownika
	 * @param user
	 * @return
	 */
	String generateActivationHash(User user);
	
	/**
	 * Generuje hash potrzebny do odzyskania has�a
	 * @param user
	 * @return
	 */
	String generatePasswordReminderHash(User user);
	
}
