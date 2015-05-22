package it.coderunner.gigs.worker.backend.mail;

import it.coderunner.gigs.model.config.MailQueue;

/**
 * Wspólny interfejs dla każdej klasy udostępniającej wysyłanie poczty e-mail w systemie
 *
 */
public interface MailSender {

	/**
	 * Metoda odpowiedzialna za wysyłanie wiadomości e-mail na podstawie obiektu mailQueue.
	 * Format maila powinien zawierać zarówno treść tekstową, jak i szablonową w oparciu o dokument HTML
	 * @param mailQueue
	 * @throws PreparingMailException
	 */
	void sendMail(MailQueue mailQueue) throws PreparingMailException;
}
