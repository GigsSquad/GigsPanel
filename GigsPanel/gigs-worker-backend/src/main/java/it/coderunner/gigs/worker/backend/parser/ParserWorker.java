package it.coderunner.gigs.worker.backend.parser;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.model.user.Country;
import it.coderunner.gigs.repository.artists.Artists;
import it.coderunner.gigs.service.artists.IArtistService;
import it.coderunner.gigs.service.gigs.IGigService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public abstract class ParserWorker extends Worker {

	private static final long serialVersionUID = -4249995395205955518L;

	@Autowired
	private IGigService gigService;

	@Autowired
	private IArtistService artistService;

	public abstract void getData() throws IOException;

	public void addConcert(String artist, String city, String spot, int day, int month, int year, String agency, String url) {

		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();

		try {
			date = format.parse(Integer.toString(day) + Integer.toString(month) + Integer.toString(year));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/*// normalizujemy nazwę klubu oraz miasta
		spot = Normalizer.normalizeSpot(spot);
		city = Normalizer.normalizeSpot(city);
*/
		//to jakoś mniej wiecej tak będzie wyglądać ale konwencja czy coś? 
		
		Artist artistObj;
		if (Artists.findAll().withName(artist).uniqueObject()!=null){
			artistObj = artistService.uniqueObject(Artists.findAll().withName(artist));
		}	else {
			artistObj= new Artist();
			artistObj.setArtist(artist);
			artistService.saveOrUpdate(artistObj);
		}
			
		Spot spotObj = new Spot();
		
		spotObj.setCity(city);
		spotObj.setSpot(spot);
		spotObj.setCountry(Country.POLAND);
		Gig gig = new Gig();
		gig.setArtist(artistObj);
		//gig.setSpot(spotObj);
		gig.setAgency(agency);
		gig.setUrl(url);
		
		gigService.save(gig);
		
		// log z informacją ze dodajemy koncert
		log.info(String.format("DODAJE %-4.4s %-12.12s %-20.20s %-40.40s", agency, city, spot, artist));
	}
}
