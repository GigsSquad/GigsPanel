package it.coderunner.gigs.worker.backend.parser;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.model.user.Country;
import it.coderunner.gigs.repository.gigs.Gigs;
import it.coderunner.gigs.service.gigs.IGigService;
import it.coderunner.gigs.util.Normalizer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Log4j
public abstract class ParserWorker extends Worker {

	private static final long serialVersionUID = -4249995395205955518L;

	@Autowired
	private IGigService gigService;

	protected String agency;
	protected Gig gig;
	Artist artistObj;
	Spot spotObj;

	public abstract void getData() throws IOException;

	public void addConcert(String artist, String city, String spot, int day, int month, int year, String agency, String url) {

		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		Date date;
		try {
			date = format.parse(Integer.toString(day) + Integer.toString(month) + Integer.toString(year));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// normalizujemy nazwę klubu oraz miasta
		spot = Normalizer.normalizeSpot(spot);
		city = Normalizer.normalizeSpot(city);

		//to jakoś mniej wiecej tak będzie wyglądać ale konwencja czy coś? 
		artistObj.setArtist(artist);
		
		spotObj.setCity(city);
		spotObj.setSpot(spot);
		spotObj.setCountry(Country.POLAND);
		
		gig.setArtist(artistObj);
		gig.setSpot(spotObj);
		gig.setAgency(agency);
		gig.setUrl(url);
		
		//jak obsłużyć że daje błąd gdy już jest taki koncert, skoro save() zwraca void
		gigService.save(gig);
		
		// log z informacją ze dodajemy koncert
		log.info(String.format("(+) %-4.4s %-12.12s %-20.20s %-40.40s", agency, city, spot, artist));
	}

	@Override
	@Scheduled(cron = "0 0 * * * *")
	public void process() {
		log.info("TestujeRepo");
		workerActivityLogEntry(TestWorker.class.getSimpleName(), "1 AM every day");
		log.info("TestujeRepo");

		log.info(gigService.count(Gigs.findAll()));

	}
}
