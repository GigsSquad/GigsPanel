package it.coderunner.gigs.webapp.controller.profile;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.service.users.IUserService;
import it.coderunner.gigs.webapp.controller.LoggedUserController;
import it.coderunner.gigs.webapp.controller.SessionPreferences;
import it.coderunner.gigs.webapp.utils.SecurityUtils;
import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j
@RequestMapping(value = { "/user" })
public class ProfileController extends LoggedUserController {

	
	@Autowired
	private IUserService userService;

	@Autowired
	private SessionPreferences sessionPreferences;
	
	@ModelAttribute("userData")
	public User userData() {
		User user = userService.uniqueObject(Users.findAll().withUsername(SecurityUtils.getLoggedUsername()));
		user.setComments(null);
		user.setStats(null);
		user.setPassword(null);
		user.setRemindPasswordHash(null);
		user.setActivationHash(null);
		
		return user;
	}

	@RequestMapping(value = { "/profile", "/profile/" })
	public String getProfile(Model model) {
		return "profile";
	}

	/*@RequestMapping(value = { "/spot/new/save" }, method = RequestMethod.POST)
	public String saveSpot(SpotsForm spotsForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		SpotsValidator validator = new SpotsValidator();
		FlashMessages flashMessages = new FlashMessages(model,
				new LocalePropertiesMessageResolver(request.getLocale()));
		validator.validate(spotsForm, result);

		if (!validator.hasErrors()) {
			try {
				System.out.println(spotsForm.getSpot().toString());
				Spot spot = spotsForm.getSpot();
				spotService.save(spot.getCity(), spot.getAddress(),
						spot.getClub(), spot.getCountry(), spot.getLat(),
						spot.getLon());
				flashMessages.addMessage("spot.save.success", Severity.SUCCESS);
				return "spot_add";
			} catch (Exception e) {
				log.warn("Warn");
				flashMessages.addMessage("spot.save.error", Severity.ERROR);
				System.out.println("Exception");
				return "spot_add";
			}

		}
		flashMessages.addMessage("spot.save.error", Severity.ERROR);
		System.out.println("po ifie");
		return "spot_add";
	}*/
}
