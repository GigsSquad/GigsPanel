package it.coderunner.gigs.worker.backend.parser;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.gig.Agency;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.model.user.Country;
import it.coderunner.gigs.repository.artists.Artists;
import it.coderunner.gigs.repository.gigs.Gigs;
import it.coderunner.gigs.repository.spots.Spots;
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

	public void addConcert(String artistString, String cityString, String clubString, int day, int month, int year, Agency agency, String url) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();

		try {
			date = format.parse(Integer.toString(year) + String.format("%02d", month) + String.format("%02d", day));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		artistString = WordUtils.capitalizeFully(artistString).trim();
		clubString = Normalizer.normalizeSpot(clubString).trim();
		cityString = Normalizer.normalizeSpot(cityString).trim();

		// tight and elegant możecie się masturbować do tych linijek <3
		Artist artist = artistService.uniqueObject(Artists.findAll().withName(artistString));
		if (artist == null) {
			artist = new Artist(artistString);
			artistService.saveOrUpdate(artist);
		}

		Spot spot = spotService.uniqueObject(Spots.findAll().withCity(cityString).withClub(clubString));
		if (spot == null) {
			spot = new Spot(cityString, clubString, Country.POLAND);
			spotService.saveOrUpdate(spot);
		}

		// porównywanie na poziomie artysty, daty i miasta, a nie spotu, szansa,
		// że spot będzie się różnić kilkoma literkami jest dość duża
		Gig gig = gigService.uniqueObject(Gigs.findAll().withArtist(artist).withDate(date).withSpot(spot));
		if (gig == null) {
			gig = new Gig(artist, spot, date, agency, url);
			log.info(String.format("(+) %-4.4s %-12.12s %-20.20s %-40.40s", agency, cityString, clubString, artistString));
			gigService.saveOrUpdate(gig);
		} else {
			log.info(String.format("(*) %-4.4s %-12.12s %-20.20s %-40.40s", agency, cityString, clubString, artistString));
		}
	}
}
