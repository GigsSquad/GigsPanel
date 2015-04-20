package it.coderunner.gigs.worker.backend;

import lombok.extern.log4j.Log4j;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@Log4j
public class PortalWorker {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext(new String[] {"/config.xml"}, PortalWorker.class);
		log.info("Backend Workers' has started!");
	}

}
