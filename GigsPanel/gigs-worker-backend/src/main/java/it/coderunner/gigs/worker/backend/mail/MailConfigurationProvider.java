package it.coderunner.gigs.worker.backend.mail;

import javax.mail.Address;
import javax.mail.Session;

/**
 * Komponent dostarczający podstawową konfigurację e-mail, zawierającą dane do wysłania wiadomości e-mail poprzez javax.mail
 *
 */
public interface MailConfigurationProvider {

	/**
	 * Zwraca zautoryzowaną sesję e-mail, podczas której zostanie wysłana wiadomość
	 * @return
	 */
	Session getCurrentSession();
	
	/**
	 * Zwraca nazwę i adres nadawcy e-mail
	 * @return
	 */
	Address getSenderAddress();
	
	/**
	 * Zwraca adres administratora, do którego zostaje wysłana kopia e-mail
	 * @return
	 */
	String getAdminRecipientAddress();
	
	/**
	 * Zwraca relatywną ścieżkę do pliku graficznego logo systemu, jakie zostanie dołączone do wiadomości e-mail
	 * @return
	 */
	String getMailLogoPath();
	
	/**
	 * Zwraca gotową wiadomość w formacie HTML, jaka zostanie wysłana do użytkownika
	 * @param plainContent  'czysty' tekst wiadomości e-mail, który zostanie wpleciony w gotowy szablon HTML
	 * @return
	 */
	String generateHTML(String plainContent);
}
