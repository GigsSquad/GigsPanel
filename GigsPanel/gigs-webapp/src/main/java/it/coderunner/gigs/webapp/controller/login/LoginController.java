package it.coderunner.gigs.webapp.controller.login;

import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.service.users.IUserService;
import it.coderunner.gigs.webapp.controller.login.form.LoginForm;
import it.coderunner.gigs.webapp.controller.login.form.LoginValidator;
import it.coderunner.gigs.webapp.mvc.FlashMessages;
import it.coderunner.gigs.webapp.mvc.Severity;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j
public class LoginController {

	@Autowired
	private IUserService userService;

	@ModelAttribute("loginForm")
	public LoginForm form() {
		return new LoginForm();
	}

	@RequestMapping(value = { "login", "/login" })
	public String login(Model model) {
		return "login";
	}

	
	//TODO
	@RequestMapping(value = { "login/ok", "/login/ok" }, method = RequestMethod.POST)
	public String login(LoginForm loginForm, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		LoginValidator validator = new LoginValidator();
		FlashMessages flashMessages = new FlashMessages(model);
		validator.validate(loginForm, result);

		if (!validator.hasErrors()) {
			try {
				userService.list(Users.findAll().loadWith("user"));

				String usr = loginForm.getUser().getUsername();
				String pwd = loginForm.getUser().getPassword();

				log.info("Username: " + usr);
				log.info("Password: " + pwd);

				flashMessages.addMessage("success.user.login", Severity.SUCCESS);
				return "index";
			} catch (Exception e) {
				log.warn("Warn");
				flashMessages.addMessage("error.user.login", Severity.ERROR);
				return "login";
			}
		}
		flashMessages.addMessage("error.user.login", Severity.ERROR);
		return "login";
	}
}
