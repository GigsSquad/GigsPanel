package it.coderunner.gigs.webapp.controller.gigs;

import it.coderunner.gigs.i18n.resolver.impl.LocalePropertiesMessageResolver;
import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.repository.artists.Artists;
import it.coderunner.gigs.repository.spots.Spots;
import it.coderunner.gigs.service.artists.IArtistService;
import it.coderunner.gigs.service.gigs.IGigService;
import it.coderunner.gigs.service.spots.ISpotService;
import it.coderunner.gigs.webapp.controller.LoggedUserController;
import it.coderunner.gigs.webapp.controller.gigs.form.GigsForm;
import it.coderunner.gigs.webapp.controller.gigs.form.GigsValidator;
import it.coderunner.gigs.webapp.mvc.FlashMessages;
import it.coderunner.gigs.webapp.mvc.Severity;

import java.util.List;

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
public class GigController extends LoggedUserController{

	@Autowired
	private IGigService gigService;
	
	@Autowired
	private IArtistService artistService;
	
	@Autowired
	private ISpotService spotService;

	@ModelAttribute("gigForm")
	public GigsForm form() {
		return new GigsForm();
	}
	
	@ModelAttribute("artists")
	public List<Artist> artists(){
		
		return artistService.list(Artists.findAll());
	}
	
	@ModelAttribute("spots")
	public List<Spot> getSpotsList(){
		
		return spotService.list(Spots.findAll());
	}
	
	@RequestMapping(value = { "/gig/new", "/gig/new/" })
	public String getConcert(Model model) {
		return "gig_edit";
	}

	@RequestMapping(value = { "/gig/new/save" }, method = RequestMethod.POST)
	public String saveConcert(GigsForm gigsForm, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		GigsValidator validator = new GigsValidator();
		FlashMessages flashMessages = new FlashMessages(model, new LocalePropertiesMessageResolver(request.getLocale()));
		validator.validate(gigsForm, result);

		if (!validator.hasErrors()) {
			try {
//				artistService.list(Artists.findAll().loadWith("gig", "gig.comment"));
//				artistService.save(gigsForm.getGig().getArtist().getName());

			//	spotService.save(gigsForm.getGig().getSpot().getCity(), gigsForm.getGig().getSpot().getClub());
				gigService.save(gigsForm.getGig());

				flashMessages.addMessage("gig.save.success", Severity.SUCCESS);
				return "gig_edit";
			} catch (Exception e) {
				log.warn("Warn");
				flashMessages.addMessage("gig.save.error", Severity.ERROR);
				return "gig_edit";
			}

		}
		flashMessages.addMessage("gig.save.error", Severity.ERROR);
		return "gig_edit";
	}
}
