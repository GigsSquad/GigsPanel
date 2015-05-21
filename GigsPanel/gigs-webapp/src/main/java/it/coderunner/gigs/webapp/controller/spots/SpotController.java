package it.coderunner.gigs.webapp.controller.spots;

import it.coderunner.gigs.repository.spots.Spots;
import it.coderunner.gigs.service.spots.ISpotService;
import it.coderunner.gigs.webapp.controller.spots.form.SpotForm;
import it.coderunner.gigs.webapp.controller.spots.form.SpotValidator;
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
public class SpotController {
	
	@Autowired
	private ISpotService spotService;
	
	@ModelAttribute("spotForm")
	public SpotForm form() {
		return new SpotForm();
	}
	
	@RequestMapping(value = { "/spot/new", "/spot/new/" })
	public String getConcert(Model model) {
		return "add_spot";
	}
	
	@RequestMapping(value = { "/spot/new/save" }, method = RequestMethod.POST)
	public String saveSpot(SpotForm spotForm, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		SpotValidator validator = new SpotValidator();
		FlashMessages flashMessages = new FlashMessages(model);
		validator.validate(spotForm, result);
		if (!validator.hasErrors()) {
			try {
				spotService.list(Spots.findAll());
				spotService.saveIfNew(spotForm.getSpot().getCity());//,spotForm.getSpot().getClub());
				flashMessages.addMessage("success.spot.save", Severity.SUCCESS);
				return "add_spot";
			} catch (Exception e) {
				log.warn("Warn");
				flashMessages.addMessage("error.spot.save", Severity.ERROR);
				return "add_spot";
			}

		}
		flashMessages.addMessage("error.spot.save", Severity.ERROR);
		return "add_spot";
	}
	
}
