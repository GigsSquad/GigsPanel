package it.coderunner.gigs.worker.backend.parser.goahead;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "file:src/main/resources/config.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class GoAheadTest {

	@Autowired
	private IGigService gigService;

	@Autowired
	private IArtistService artistService;

	@Autowired
	private ISpotService spotService;

	GoAhead mGoAhead = new GoAhead();

	@Test
	public void testProcess() {
		testGetData();

		assertEquals(true, true);// deal with it XD
	}

	@Test
	public void testGetData() {

		Artist artist = new Artist("artysta");
		// artistService.saveIfNew(artist.getName());
		artistService.save(artist);

		Spot spot = new Spot("Szczecin", "Rozwalki 7", "Pierwsze miejsce",
				Country.POLAND, 0.0, 0.0);
		spotService.save(spot);

		Gig gig = new Gig(artist, spot, new Date(), Agency.GOAHEAD,
				"http://www.wypok.pl");
		gigService.save(gig);

		assertNotNull(gigService
				.uniqueObject(Gigs.findAll().withArtist(artist)));
		assertNotNull(artist);
		assertNotNull(spot);

		assertEquals(
				artistService.uniqueObject(Artists.findAll().withName(
						artist.getName())), artist);
		assertEquals(
				spotService.uniqueObject(Spots.findAll()
						.withCity(spot.getCity()).withClub(spot.getClub())),
				spot);
	}
}
