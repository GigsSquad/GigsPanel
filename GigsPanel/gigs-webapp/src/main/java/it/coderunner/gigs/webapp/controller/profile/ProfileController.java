package it.coderunner.gigs.webapp.controller.profile;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.service.users.IUserService;
import it.coderunner.gigs.webapp.controller.LoggedUserController;
import it.coderunner.gigs.webapp.controller.profile.form.AvatarForm;
import it.coderunner.gigs.webapp.utils.SecurityUtils;

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
@RequestMapping(value = { "/user" })
public class ProfileController extends LoggedUserController {

	@Autowired
	private IUserService userService;

	@ModelAttribute("userData")
	public User userData() {
		User user = userService.uniqueObject(Users.findAll().withUsername(
				SecurityUtils.getLoggedUsername()));
		user.setComments(null);
		user.setStats(null);
		user.setPassword(null);
		user.setRemindPasswordHash(null);
		user.setActivationHash(null);

		return user;
	}
	
	@ModelAttribute
	public AvatarForm avatar(){
		return new AvatarForm();
	}

	@RequestMapping(value = { "/profile", "/profile/" })
	public String getProfile(Model model) {
		return "profile";
	}

	@RequestMapping(value = { "/profile/upload", "/profile/upload/" }, method = RequestMethod.POST)//, consumes = "text/plain", produces ="text/html")
	public String saveAvatar(@ModelAttribute("avatarForm") AvatarForm avatarForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		System.out.println(avatarForm.getFile().toString());
		System.out.println("w saveAvatar()");
		return "profile"; 
	}
}
