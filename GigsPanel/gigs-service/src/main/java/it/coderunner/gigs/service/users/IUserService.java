package it.coderunner.gigs.service.users;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.model.user.UserStatus;
import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.service.IService;

import java.util.List;
import java.util.Locale;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService, IService{

	void delete(User user);
	
	/**
	 * Metoda sprawdzająca czy użytkownik z podanym mailem istnieje w repozytorium
	 * @param email adres email do sprawdzenia
	 * @return true jeśli istnieje taki użytkownik
	 */
	boolean isEmailExists(String email);
	
	/**
	 * Metoda zapisująca nowego użytkownika do repozytorium. 
	 * Wywoływana podczas jego rejestrowania.
	 * @param user
	 * @param inquiry - jeżeli w sesji użytkownik stworzył zapytanie ofertowe
	 * @param project 
	 * @param teamInvitationHash - jeżeli użytkownik zarejestrował się poprzez link wysłany mu na email z zaproszeniem do zespołu
	 * @param projectSponsorInvitationHash - jeżeli użytkownik zarejestrował się poprzez link wysłany mu na email z zaproszeniem do projektu
	 * @throws Exception 
	 */
	void register(User user, Locale locale);
	
	/**
	 * Metoda zapisująca zmiany danych użytkownika. 
	 * @param user
	 */
	void update(User user);
	
	/**
	 * Metoda zmieniająca hasło użytkownika 
	 * @param user
	 * @param plainPassword
	 */
	void changePassword(User user, String plainPassword);
	
	/**
	 * Metoda przypominająca hasło użytkownika
	 * @param user
	 * @param ipAddress 
	 */
	void remindPass(User user, String ipAddress);
	
	/**
	 * Metoda wywoływana po poprawnym zalogowaniu się użytkownika.
	 * @param username
	 * @param inquiry 
	 * @return user 
	 */
	User afterAuthentication(String username);
	
	/**
	 * Aktywuje użytkownika wskazanym hashem
	 * @param activationHash
	 * @return - zwraca aktywowanego użytkownika
	 */
	User activateUser(String activationHash);
	
	void encryptPasswords(User user);
	
	boolean exists(Users users);
	
	void saveOrUpdate(User user);
	
	void save(User user);
	
	boolean saveIfNew(User user);

	List<User> list(Users users);

	long count(Users users);
	
	User uniqueObject(Users users);
}
