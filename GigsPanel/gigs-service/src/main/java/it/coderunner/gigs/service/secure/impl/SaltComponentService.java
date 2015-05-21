package it.coderunner.gigs.service.secure.impl;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.users.IUserRepository;
import it.coderunner.gigs.service.secure.ISaltComponentService;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SaltComponentService implements ISaltComponentService {

	private static final long serialVersionUID = -1423359953987354646L;

	@Autowired
	private IUserRepository userDAO;

	public String generateActivationHash(User user) {
		String token = user.getEmail();
		return new Md5PasswordEncoder().encodePassword(token, "!activationHash");
	}

	public String generatePasswordReminderHash(User user) {
		Random rand = new Random();
		String token = user.getEmail() + rand.nextInt() + user.getUsername() + rand.nextGaussian();
		return new Md5PasswordEncoder().encodePassword(token, "$passwordReminder");
	}
	
}
