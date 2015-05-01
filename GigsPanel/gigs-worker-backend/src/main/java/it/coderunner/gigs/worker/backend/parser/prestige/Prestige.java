package it.coderunner.gigs.worker.backend.parser.prestige;

import it.coderunner.gigs.worker.backend.parser.ParserWorker;

import java.io.IOException;

import lombok.extern.log4j.Log4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class Prestige extends ParserWorker {

	private static final long serialVersionUID = 4777939950955857207L;
	private String URL_PRESTIGE = "http://bilety.imprezyprestige.com/rezerwacja/termin.html";

	@Override
	public void getData() throws IOException {
		Document doc = Jsoup.connect(URL_PRESTIGE).get();
		Elements concertData = doc.getElementsByClass("termin");
		String conUrl = "http://bilety.imprezyprestige.com/rezerwacja/termin.html";
		String conSpot = null;
		String conCity = null;
		for (Element el : concertData) {

			String conName = el.getElementsByClass("tytul").first().text();
			String conDate = el.getElementsByClass("data").first().text();
			// String conPlace = .first().text();
			String[] conPlace = el.getElementsByClass("obiekt").html().split("<br>"); // place==city+spot
			// System.out.println(conPlace.length);

			try {
				conSpot = conPlace[0];
				conCity = conPlace[1];
			} catch (ArrayIndexOutOfBoundsException exc) {
				conCity = conPlace[0];
			}

			int conYear = Integer.valueOf(conDate.split(" ")[0].split("-")[0]);

			int conMonth = Integer.valueOf(conDate.split(" ")[0].split("-")[1]);

			int conDay = Integer.valueOf(conDate.split(" ")[0].split("-")[2]);

			// System.out.println(conDay+"\n"+ conMonth+"\n"+conYear+"\n");
			// System.out.println(conCity+"$$$"+conSpot);
			addConcert(conName, conCity, conSpot, conDay, conMonth, conYear, "PRESTIGE", conUrl);
			// agencyName, conUrl);
		}
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void process() {
		try {
			log.info("Uruchamiam Prestige");
			workerActivityLogEntry(Prestige.class.getSimpleName(), "Every hour of every day");
			getData();
			log.info("Skończyłem");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
