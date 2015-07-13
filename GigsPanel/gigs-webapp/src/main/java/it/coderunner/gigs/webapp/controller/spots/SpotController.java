package it.coderunner.gigs.webapp.controller.spots;

import it.coderunner.gigs.i18n.resolver.impl.LocalePropertiesMessageResolver;
import it.coderunner.gigs.service.spots.ISpotService;
import it.coderunner.gigs.webapp.controller.LoggedUserController;
import it.coderunner.gigs.webapp.controller.spots.form.SpotsForm;
import it.coderunner.gigs.webapp.controller.spots.form.SpotsValidator;
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
public class SpotController extends LoggedUserController{
	
	@Autowired
	private ISpotService spotService;
	
	@ModelAttribute("spotForm")
	public SpotsForm form() {
		return new SpotsForm();
	}
	
	@RequestMapping(value = { "/spot/new", "/spot/new/" })
	public String getConcert(Model model) {
		return "spot_add";
	}

	@RequestMapping(value = { "/spot/new/save" }, method = RequestMethod.POST)
	public String saveSpot(SpotsForm spotsForm, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		SpotsValidator validator = new SpotsValidator();
		FlashMessages flashMessages = new FlashMessages(model, new LocalePropertiesMessageResolver(request.getLocale()));
		validator.validate(spotsForm, result);

		if (!validator.hasErrors()) {
			try {

				spotService.save(spotsForm.getSpot().getCity(), spotsForm.getSpot().getAddress(), spotsForm.getSpot().getClub());

				flashMessages.addMessage("spot.save.success", Severity.SUCCESS);
				return "spot_add";
			} catch (Exception e) {
				log.warn("Warn");
				flashMessages.addMessage("spot.save.error", Severity.ERROR);
				return "spot_add";
			}

		}
		flashMessages.addMessage("spot.save.error", Severity.ERROR);
		return "spot_add";
	}
}
