package it.coderunner.gigs.worker.backend.parser;

import static org.junit.Assert.*;
import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.gig.Agency;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.model.user.Country;
import it.coderunner.gigs.repository.artists.Artists;
import it.coderunner.gigs.repository.gigs.Gigs;
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
public class ParserWorkerTest {

	@Autowired
	private IGigService gigService;

	@Autowired
	private IArtistService artistService;

	@Autowired
	private ISpotService spotService;
	
	private Gig gig;
	
	private Artist artist;
	
	public void init(){
		
	}

	@Test
	public void testAddConcert() {
		artist = new Artist("Artysta1");
		artistService.save(artist);
		
		Spot spot = new Spot("Warszawa", "Klub", Country.AFGHANISTAN);
		spotService.save(spot);
		
		gig = new Gig(artist, spot, new Date(), Agency.ALTERART, "http://www.www.pl");
		gigService.save(gig);
		
		assertNotNull(gigService.uniqueObject(Gigs.findAll().withArtist(artist)));
		assertEquals(artistService.uniqueObject(Artists.findAll().withName(artist.getName())), artist);
	}

	@Test
	public void testProcess() {
		fail("Not yet implemented");
	}

	@Test
	public void testWorkerActivityLogEntry() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAlive() {
		fail("Not yet implemented");
	}

}
