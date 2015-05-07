package it.coderunner.gigs.worker.backend.parser.goahead;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
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

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GoAheadTest {

	@Autowired
	private IGigService gigService;

	@Autowired
	private IArtistService artistService;

	@Autowired
	private ISpotService spotService;

	@Test
	public void testProcess() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetData() {
		Artist artist = new Artist("jakis artysta");
		artistService.saveIfNew(artist.getName());

		Spot spot = new Spot("Szczecin", "Pierwsze miejsce", Country.POLAND);
		spotService.save(spot);

		Gig gig = new Gig(artist, spot, new Date(), Agency.ALTERART, "http://www.wypok.pl");
		gigService.save(gig);

		assertNotNull(gigService.uniqueObject(Gigs.findAll().withArtist(artist)));
		assertEquals(artistService.uniqueObject(Artists.findAll().withName(artist.getName())), artist);
		assertEquals(spotService.uniqueObject(Spots.findAll().withCity(spot.getCity()).withClub(spot.getClub())), spot);
	}

	@Test
	public void testGoAhead() {
		fail("Not yet implemented");
	}

}
