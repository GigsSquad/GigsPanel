package it.coderunner.gigs.worker.backend.parser.kayax;

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
public class Kayax extends ParserWorker {

	private static final long serialVersionUID = 8994810917723626203L;

	@Override
	public void getData() throws IOException {
		Elements rows;
		int counter = -1;

		do {
			String urlKayax = "http://kayax.net/kalendarz/";
			counter++;
			urlKayax = urlKayax + counter + "/";
			Document doc = Jsoup.connect(urlKayax).timeout(10000).get();

			Element table = doc.getElementsByTag("table").get(0);
			rows = table.select("tr");

			String conName = "", conCity = "", conSpot = "";
			for (int i = 2; i < rows.size(); i++) {
				Element row = rows.get(i);
				String conArtistCity = row.getElementsByAttribute("style").first().text();
				String conDate = row.getElementsByClass("event_date").text().split(" ")[0];

				int conYear = Integer.valueOf(conDate.split("-")[0]);
				int conMonth = Integer.valueOf(conDate.split("-")[1]);
				int conDay = Integer.valueOf(conDate.split("-")[2]);

				String conUrl = row.getElementsByTag("a").first().attr("href");
				conUrl = "http://kayax.net" + conUrl;
				conCity = "";
				conSpot = "";
				conName = "";
				String[] conArtistCityArray = conArtistCity.split("-");
				if (conArtistCityArray.length == 3) { // jak sa oddzielone
														// myslnikami i są
														// wszyskie 3
					conName = conArtistCityArray[0];
					conCity = conArtistCityArray[1];
					conSpot = conArtistCityArray[2];
					addConcert(conName, conCity, conSpot, conDay, conMonth, conYear, "KAYAX", conUrl);
				}

				if (conArtistCityArray.length == 2) // czasem nie ma klubu
													// podanego
				{

					if (conArtistCityArray[0].contains(",")) {// to jest
																// Hey,lodz np.
																// albo hey,
																// unplugged
						String[] conNameCity = conArtistCityArray[0].split(",");
						conName = conNameCity[0];
						if (!conNameCity[1].contains("unplugged")) {
							conCity = conNameCity[1];
							conSpot = conArtistCityArray[1];
						} else {
							conCity = conArtistCityArray[1];
							conSpot = row.getElementsByTag("a").get(1).text().split(",")[0];
						}
						addConcert(conName, conCity, conSpot, conDay, conMonth, conYear, "KAYAX", conUrl);
					} else if (!conArtistCityArray[1].toLowerCase().contains("impreza")) {
						conName = conArtistCityArray[0];
						conCity = conArtistCityArray[1];
						conSpot = row.getElementsByTag("a").get(1).text().split(",")[0];
						addConcert(conName, conCity, conSpot, conDay, conMonth, conYear, "KAYAX", conUrl);
					}
				}

			}
		} while (rows.size() > 2);

	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void process() {
		try {
			log.info("Uruchamiam Kayax");
			workerActivityLogEntry(Kayax.class.getSimpleName(), "Every hour of every day");
			getData();
			log.info("Skończyłem");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
