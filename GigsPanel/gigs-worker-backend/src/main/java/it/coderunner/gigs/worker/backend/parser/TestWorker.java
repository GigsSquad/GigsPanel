/**
 * 
 */
package it.coderunner.gigs.worker.backend.parser;

import it.coderunner.gigs.repository.gigs.Gigs;
import it.coderunner.gigs.service.gigs.IGigService;
import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author evelan
 * 
 */
@Log4j
@Component
public class TestWorker extends Worker{

	private static final long serialVersionUID = 9013708701686852259L;
	
	@Autowired
	private IGigService gigService;

	@Override
	@Scheduled(fixedDelay=10000)
	public void process() {
		log.info("TestujeRepo");
		workerActivityLogEntry(TestWorker.class.getSimpleName(), "1 AM every day");
		log.info("TestujeRepo");
		
		log.info(gigService.count(Gigs.findAll()));
		
	}
}
