package it.coderunner.gigs.webapp.controller.login;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.coderunner.gigs.i18n.resolver.impl.LocalePropertiesMessageResolver;
import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.service.users.IUserService;
import it.coderunner.gigs.webapp.controller.SessionPreferences;
import it.coderunner.gigs.webapp.controller.login.form.LoginForm;
import it.coderunner.gigs.webapp.controller.login.form.LoginValidator;
import it.coderunner.gigs.webapp.mvc.FlashMessages;
import it.coderunner.gigs.webapp.mvc.Severity;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController implements Serializable{

	
	private static final long serialVersionUID = -2482949663952517699L;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private SessionPreferences sessionPreferences;

	@Autowired
	private RememberMeServices rememberMeServices;

	@Autowired
	private IUserService userService;

	@Autowired
	private LocaleResolver localeResolver;

	@ModelAttribute("loginForm")
	public LoginForm form() {
		return new LoginForm();
	}

	@RequestMapping(value = { "/login", "/login/" })
	public String login(Model model) {
		return "login";
	}
	@SuppressWarnings("finally")
	@RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.POST)
	public String login(LoginForm loginForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response) {
		
		LoginValidator validator = new LoginValidator();
		FlashMessages flashMessages = new FlashMessages(model,
				new LocalePropertiesMessageResolver(
						localeResolver.resolveLocale(request)));
		System.out.println(localeResolver.resolveLocale(request));

		validator.validate(loginForm, result);
		
		if (!validator.hasErrors()) {
			String resultView = null;
			Authentication authenticate = null;
			try {
				// sprawdzamy czy haslo i user sie zgadza (z sola)
				authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUser().getUsername(), loginForm.getUser().getPassword()));
				if(authenticate.isAuthenticated()){
					// rafalke robi komentarze
					SecurityContextHolder.getContext().setAuthentication(authenticate);
					HttpSession session = request.getSession(true);
					session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
					User user = userService.afterAuthentication(authenticate.getName());
					sessionPreferences.setDisplayName(user.getUsername());
					resultView = "redirect:/user/index";
				}
			} catch (BadCredentialsException e) {
				log.warn("credentials", e);
				flashMessages.clearMessages();
				flashMessages.addMessage("user.login.error.credentials", Severity.ERROR);
				resultView = "login";
				
			} catch (AccountExpiredException e){
				log.warn("expired", e);
				flashMessages.clearMessages();
				flashMessages.addMessage("user.login.error.expired", Severity.ERROR);
				resultView = "login";
				
			} catch (LockedException e){
				log.warn("locked", e);
				flashMessages.clearMessages();
				flashMessages.addMessage("user.login.error.locked", Severity.ERROR);
				resultView = "login";
			} catch (Exception e){
				log.warn("error", e);
				flashMessages.clearMessages();
				flashMessages.addMessage("user.login.error", Severity.ERROR);
				resultView = "login";
			} finally{
				return resultView;
			}
		}
		return "login";
	}
}
