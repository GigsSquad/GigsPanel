package it.coderunner.gigs.webapp.controller.artists;

import it.coderunner.gigs.i18n.resolver.impl.LocalePropertiesMessageResolver;
import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.tag.Tag;
import it.coderunner.gigs.repository.tags.Tags;
import it.coderunner.gigs.service.artists.IArtistService;
import it.coderunner.gigs.service.tags.ITagService;
import it.coderunner.gigs.webapp.controller.LoggedUserController;
import it.coderunner.gigs.webapp.controller.artists.form.ArtistsForm;
import it.coderunner.gigs.webapp.controller.artists.form.ArtistsValidator;
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
public class ArtistController extends LoggedUserController{
	
	@Autowired
	private IArtistService artistService;
	
	@Autowired
	private ITagService tagService;
	
	@ModelAttribute("artistForm")
	public ArtistsForm form() {
		return new ArtistsForm();
	}
	@ModelAttribute("tags")
	public List<Tag> getTagList(){
		return tagService.list(Tags.findAll());
	}
	
	@RequestMapping(value = { "/artist/new", "/artist/new/" })
	public String getArtist(Model model) {
		return "artist_add";
	}

	@RequestMapping(value = { "/artist/new/save" }, method = RequestMethod.POST)
	public String saveArtist(ArtistsForm artistsForm, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		ArtistsValidator validator = new ArtistsValidator();
		FlashMessages flashMessages = new FlashMessages(model, new LocalePropertiesMessageResolver(request.getLocale()));
		validator.validate(artistsForm, result);

		if (!validator.hasErrors()) {
			try {

				Artist artist = new Artist(artistsForm.getArtistName(),new Tag(artistsForm.getTag()));
				artistService.save(artist);
				
				flashMessages.addMessage("artist.save.success", Severity.SUCCESS);
				return "artist_add";
			} catch (Exception e) {
				log.warn("Warn");
				flashMessages.addMessage("artist.save.error", Severity.ERROR);
				return "artist_add";
			}

		}
		flashMessages.addMessage("artist.save.error", Severity.ERROR);
		return "artist_add";
	}
}
