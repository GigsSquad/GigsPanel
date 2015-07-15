package it.coderunner.gigs.webapp.controller;

import it.coderunner.gigs.repository.artists.Artists;
import it.coderunner.gigs.repository.gigs.Gigs;
import it.coderunner.gigs.repository.gigs.IGigRepository;
import it.coderunner.gigs.repository.spots.Spots;
import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.service.artists.IArtistService;
import it.coderunner.gigs.service.gigs.IGigService;
import it.coderunner.gigs.service.spots.ISpotService;
import it.coderunner.gigs.service.users.IUserService;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j
@RequestMapping(value = { "/user" })
public class HomeController extends LoggedUserController{
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISpotService spotService;
	
	@Autowired
	private IArtistService artistService;
	
	@Autowired
	private IGigService gigService;
	
	
	@ModelAttribute("userCounter")
	public int userCounter() {
		return userService.list(Users.findAll()).size();
	}
	
	@ModelAttribute("spotCounter")
	public int spotCounter(){
		return spotService.list(Spots.findAll()).size();
	}
	
	@ModelAttribute("artistCounter")
	public int artistCounter(){
		return artistService.list(Artists.findAll()).size();
	}
	
	@ModelAttribute("gigCounter")
	public int gigCounter(){
		return gigService.list(Gigs.findAll()).size();
	}

	@RequestMapping(value={"", "/", "index", "/index/"}, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		log.info("Żyję!!!");
		return "index";
	}

}
