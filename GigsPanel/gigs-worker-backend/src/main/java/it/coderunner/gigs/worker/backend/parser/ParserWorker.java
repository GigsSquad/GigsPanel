package it.coderunner.gigs.worker.backend.parser;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.gig.Agency;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.service.artists.IArtistService;
import it.coderunner.gigs.service.gigs.IGigService;
import it.coderunner.gigs.service.spots.ISpotService;
import it.coderunner.gigs.util.Normalizer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public abstract class ParserWorker extends Worker {

	private static final long serialVersionUID = -4249995395205955518L;

	@Autowired
	private IGigService gigService;

	@Autowired
	private IArtistService artistService;

	@Autowired
	private ISpotService spotService;

	public abstract void getData() throws IOException;

	public void addConcert(String artistString, String city, String club, int day, int month, int year, Agency agency, String url) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();

		try {
			date = format.parse(Integer.toString(year) + String.format("%02d", month) + String.format("%02d", day));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		artistString = WordUtils.capitalizeFully(artistString).trim();
		club = Normalizer.normalizeSpot(club).trim();
		city = Normalizer.normalizeSpot(city).trim();

		// tight and elegant możecie się masturbować do tych linijek <3
		Artist artist = artistService.saveIfNew(artistString);
		Spot spot = spotService.saveIfNew(city, club);
		
		gigService.saveIfNew(new Gig(artist, spot, date, agency, url));
	}
}
