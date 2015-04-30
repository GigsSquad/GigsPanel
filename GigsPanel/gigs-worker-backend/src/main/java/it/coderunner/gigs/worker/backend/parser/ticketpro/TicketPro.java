package it.coderunner.gigs.worker.backend.parser.ticketpro;

import it.coderunner.gigs.model.gig.Agency;
import it.coderunner.gigs.worker.backend.parser.ParserWorker;

import java.io.IOException;
import java.net.SocketTimeoutException;

import lombok.extern.log4j.Log4j;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class TicketPro extends ParserWorker {

	private static final long serialVersionUID = 2109565724003236585L;

	@Override
	public void getData() throws IOException {
		System.out.println("getDate");
		String urlParse = "http://www.ticketpro.pl/jnp/muzyka/index.html?page=1";
		String urlParseName = ""; // potrzebne do parsowania podstron
		String conCity, conSpot;
		int conDay, conMonth, conYear;
		do {
			Document doc = null;
			Elements concertData = null;
			try {
				doc = Jsoup.connect(urlParse).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(1000).get();
				concertData = doc.getElementsByClass("eventInfo");
			} catch (HttpStatusException e) {
				log.info("Błąd 404, przy pobieraniu z URL = " + urlParse + urlParseName, e);
			} catch (SocketTimeoutException tmoe) {
				doc = Jsoup.connect(urlParse).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").timeout(100000).get();
				concertData = doc.getElementsByClass("eventInfo");
			}

			// dbm.updateHash("TicketPro", currentHash);

			for (Element el : concertData) {
				String conName = el.getElementsByTag("a").first().text();

				String conUrl = el.getElementsByTag("a").first().attr("href");
				conUrl = "http://www.ticketpro.pl" + conUrl;

				String location = el.getElementsByClass("fn").text(); // City +
																		// spot
				if (!location.equals("")) // nie ma lokalizacji na gĹ‚Ăłwnej
											// stronie->odwiedzamy szczegĂłĹ‚y
				{

					try { // jezeli coĹ› jest zapisane niestandardowo to omijamy
						conCity = location.split(",")[1];
						conSpot = location.split(",")[0];

						String conDate = el.getElementsByClass("dtstart").first().text();

						String[] conDateArray = conDate.split("\\.");

						conDay = Integer.parseInt(conDateArray[0]);
						conMonth = Integer.parseInt(conDateArray[1]);
						conYear = Integer.parseInt(conDateArray[2]);
					} catch (ArrayIndexOutOfBoundsException e) {
						log.info("wygląda na obsługiwany, lol", e);
						continue;
					}

					addConcert(conName, conCity, conSpot, conDay, conMonth, conYear, Agency.TICKETPRO, conUrl);
				} else
					// jest wiecej niz jeden koncert
					// System.out.println(conName);
					getOtherLocalisation(conName, conUrl);
			}

			try {
				urlParse = doc.getElementsByClass("normal").last().attr("href");
				urlParse = "http://www.ticketpro.pl" + urlParse;
				urlParseName = doc.getElementsByClass("normal").last().text();
			} catch (Exception e) {
				break;
			}

		} while (urlParseName.equals("Następny"));
	}

	private void getOtherLocalisation(String conName, String detailInfo) throws IOException {
		Document doc = Jsoup.connect(detailInfo).timeout(1000000).get();
		Elements concertData = doc.getElementsByClass("info");

		for (Element el : concertData) {

			// url do szczegolow koncertu
			String conUrl = el.getElementsByTag("a").first().attr("href");
			conUrl = "http://www.ticketpro.pl" + conUrl;

			String conDate = el.getElementsByClass("date").first().text();

			if (conDate.split(" - ").length <= 1) //
			{

				try {
					String[] conDateArray = conDate.split("\\.");

					int conDay = Integer.parseInt(conDateArray[0]);
					int conMonth = Integer.parseInt(conDateArray[1]);
					int conYear = Integer.parseInt(conDateArray[2]);

					String conLocation = el.getElementsByTag("p").first().text();
					String[] conLocationArray = conLocation.split(",");
					String conSpot = conLocationArray[0];
					String conCity = conLocationArray[1];
					addConcert(conName, conCity, conSpot, conDay, conMonth, conYear, Agency.TICKETPRO, conUrl);

				} catch (ArrayIndexOutOfBoundsException e) {
					log.info("wygląda na obsługiwany, lol", e);
					break;
				}
			}
		}
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void process() {
		try {
			log.info("Uruchamiam TicketPro");
			workerActivityLogEntry(TicketPro.class.getSimpleName(), "Every hour of every day");
			getData();
			log.info("Skończyłem");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
