package it.coderunner.gigs.webapp.controller.register;

import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.service.users.IUserService;
import it.coderunner.gigs.webapp.controller.register.form.RegisterForm;
import it.coderunner.gigs.webapp.controller.register.form.RegisterValidator;
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
public class RegisterController {

	@Autowired
	private IUserService userService;

	@ModelAttribute("registerForm")
	public RegisterForm form() {
		return new RegisterForm();
	}

	@RequestMapping(value = { "/register", "/register/" })
	public String register(Model model) {
		return "register";
	}

	
	//TODO
	@RequestMapping(value = { "/register", "/register/" }, method = RequestMethod.POST)
	public String register(RegisterForm registerForm, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		RegisterValidator validator = new RegisterValidator();
		FlashMessages flashMessages = new FlashMessages(model);
		validator.validate(registerForm, result);

		if (!validator.hasErrors()) {
			try {
				userService.list(Users.findAll().loadWith("user"));

				String usr = registerForm.getUser().getUsername();
				String pwd = registerForm.getUser().getPassword();

				log.info("Username: " + usr);
				log.info("Password: " + pwd);

				flashMessages.addMessage("success.user.register", Severity.SUCCESS);
				return "index";
			} catch (Exception e) {
				log.warn("Warn");
				flashMessages.addMessage("error.user.register", Severity.ERROR);
				return "register";
			}
		}
		flashMessages.addMessage("error.user.register", Severity.ERROR);
		return "register";
	}
}
