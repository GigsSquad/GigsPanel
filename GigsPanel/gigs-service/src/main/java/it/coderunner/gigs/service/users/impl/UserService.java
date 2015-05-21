package it.coderunner.gigs.service.users.impl;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.model.user.UserRole;
import it.coderunner.gigs.model.user.UserStatus;
import it.coderunner.gigs.repository.users.IUserRepository;
import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.service.secure.ISaltComponentService;
import it.coderunner.gigs.service.users.IUserService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@Transactional(rollbackFor = Exception.class)
public class UserService implements IUserService {

	private static final long serialVersionUID = -7007444203780713873L;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ISaltComponentService saltComponentService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void delete(User user) {
		userRepository.delete(user);

	}

	@Override
	public void saveOrUpdate(User user) {
		userRepository.saveOrUpdate(user);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public boolean saveIfNew(User user) {
		return false;
	}

	@Override
	public List<User> list(Users users) {
		return userRepository.findAll().merge(users).list();
	}

	@Override
	public long count(Users users) {
		return userRepository.findAll().merge(users).count();
	}

	@Override
	public User uniqueObject(Users users) {
		return userRepository.findAll().merge(users).uniqueObject();
	}

	@Override
	public boolean isEmailExists(String email) {
		User user = userRepository.findAll().withEmail(email).uniqueObject();
		// TODO Criteria dla Usera do poprawki
		userRepository.evict(user);
		return user != null;
	}

	@Override
	public void register(User user, Locale locale) {
		user.add(UserRole.ROLE_ADMIN, UserRole.ROLE_USER);
		String activationHash = saltComponentService.generateActivationHash(user);
		user.setActivationHash(activationHash);
		user.setStatus(UserStatus.PENDING);
		userRepository.save(user);

	}

	@Override
	public void update(User user) {
		userRepository.update(user);

	}

	@Override
	public void changePassword(User user, String plainPassword) {
		user.setPassword(plainPassword);
		user.setRemindPasswordHash(null);
		if (user.hasStatus(UserStatus.PENDING)) {
			user.setStatus(UserStatus.ACTIVE);
		}
		userRepository.update(user);
	}

	private User getByUsername(String username) {
		//TODO Criteria
		return userRepository.findAll().withUsername(username).uniqueObject();
	}

	/**
	 * Zwraca implementację {@link UserDetails} za pomocą loginu
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("");
		}
		boolean isUserActive = user.hasStatus(UserStatus.ACTIVE);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), isUserActive, isUserActive, isUserActive,
				isUserActive, getAuthorities(user));
	}

	/**
	 * Zwraca kolekcję {@link GrantedAuthority} na bazie zbioru {@link Role}
	 * użytkownika
	 * 
	 * @param user
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (UserRole role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		return authorities;
	}

	@Override
	public void remindPass(User user, String ipAddress) {
		// generujemy unikalny hash dla użytkownika
		String hash = saltComponentService.generatePasswordReminderHash(user);
		user.setRemindPasswordHash(hash);
		userRepository.update(user);
	}

	@Override
	public User afterAuthentication(String username) {
		User user = userRepository.findAll().withUsername(username).loadWith("coopProfile").uniqueObject();
		user.setRemindPasswordHash(null);
		//powinno updateować automatycznie, bez konieczności wywołania update z repozytorium
		return user;
	}

	@Override
	public User activateUser(String activationHash) {
		User user = userRepository.findAll().withActivationHash(activationHash).withStatus(UserStatus.PENDING).uniqueObject();
		//TODO User Criteria
		if (user!=null) {
			user.setStatus(UserStatus.ACTIVE);
			userRepository.update(user);
			return user;
		} else
			return null;
	}

	@Override
	public boolean exists(Users users) {
		User user = userRepository.findAll().merge(users).uniqueObject();
		userRepository.evict(user);
		return user!=null;
	}

}
