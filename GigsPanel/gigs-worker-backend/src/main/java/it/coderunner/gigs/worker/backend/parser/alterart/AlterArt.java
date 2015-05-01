package it.coderunner.gigs.worker.backend.parser.alterart;

import it.coderunner.gigs.worker.backend.parser.ParserWorker;

import java.io.IOException;
import java.util.ArrayList;

import lombok.extern.log4j.Log4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class AlterArt extends ParserWorker {

	private static final long serialVersionUID = -8783332355923666434L;

	@Override
	public void getData() throws IOException {
		String URL_ALTERART = "http://alterart.pl/pl/Archiwum";
		Document doc = Jsoup.connect(URL_ALTERART).timeout(60000).get();
		Element allEvents = doc.getElementById("all_events");

		Elements names = allEvents.getElementsByClass("concert-box-data-name");
		ArrayList<String> urls = new ArrayList<>();
		for (Element el : names)
			urls.add(el.select("a").attr("href"));
		Elements datesPlaces = allEvents.getElementsByClass("concert-box-data-date");
		Elements dates = new Elements();
		Elements spots = new Elements();
		for (int i = 0; i < datesPlaces.size(); i++) {
			if (i % 2 == 0)
				dates.add(datesPlaces.get(i));
			else
				spots.add(datesPlaces.get(i));
		}
		Elements cities = allEvents.getElementsByClass("concert-box-data-city");
		for (int i = 0; i < names.size(); i++) {
			String dateStrArr[] = dates.get(i).text().split("\\.");
			int day = Integer.valueOf(dateStrArr[0]);
			int month = Integer.valueOf(dateStrArr[1]);
			int year = Integer.valueOf(dateStrArr[2]);
			addConcert(names.get(i).text(), cities.get(i).text(), spots.get(i).text(), day, month, year, "ALTERART", urls.get(i));
		}
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void process() {
		try {
			log.info("Uruchamiam AlterArt");
			workerActivityLogEntry(AlterArt.class.getSimpleName(), "Every hour of every day");
			getData();
			log.info("Skończyłem");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
