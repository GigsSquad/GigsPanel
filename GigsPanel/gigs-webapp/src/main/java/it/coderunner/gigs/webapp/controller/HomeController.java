package it.coderunner.gigs.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j
public class HomeController {
	
	@RequestMapping(value={"", "/"}, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request){
		
		log.info("Żyję!!!");
		return null;
	}

}
