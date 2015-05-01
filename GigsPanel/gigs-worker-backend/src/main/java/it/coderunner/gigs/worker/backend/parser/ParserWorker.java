package it.coderunner.gigs.worker.backend.parser;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.gig.Gig;
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

	@Autowired
	// private ISpotService spotService;
	public abstract void getData() throws IOException;

	public void addConcert(String artistString, String cityString, String spotString, int day, int month, int year, String agencyString, String url) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		System.out.println();
		try {
			date = format.parse(Integer.toString(year) + String.format("%02d", month) + String.format("%02d", day));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/*
		 * // normalizujemy nazwę klubu oraz miasta spot =
		 * Normalizer.normalizeSpot(spot); city =
		 * Normalizer.normalizeSpot(city);
		 */
		// to jakoś mniej wiecej tak będzie wyglądać ale konwencja czy coś?

		Artist artist;
		if (Artists.findAll().withName(artistString).uniqueObject() != null) {
			artist = artistService.uniqueObject(Artists.findAll().withName(artistString));
		} else {
			artist = new Artist();
			artist.setName(artistString);
			artistService.saveOrUpdate(artist);
		}

		// Spot spot = new Spot();
		// spotService.saveOrUpdate(spot);

		// spot.setCity(cityString);
		// spot.setSpot(spotString);
		// spot.setCountry(Country.POLAND);

		gigService.save(new Gig(artist, null, date, agencyString, url));

		// log z informacją ze dodajemy koncert
		log.info(String.format("DODAJE %-4.4s %-12.12s %-20.20s %-40.40s", agencyString, cityString, spotString, artistString));
	}
}
