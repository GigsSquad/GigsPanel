package it.coderunner.gigs.worker.backend.parser.goahead;

import it.coderunner.gigs.worker.backend.parser.ParserWorker;
import it.coderunner.gigs.worker.backend.parser.TestWorker;

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
public class GoAhead extends ParserWorker {

	private static final long serialVersionUID = -9164638291706534689L;

	private final static String URL_GOAHEAD = new String("http://www.go-ahead.pl/pl/koncerty.html");

	public GoAhead() {
		super();
	}

	@Override
	public void getData() throws IOException {
		Document doc = Jsoup.connect(URL_GOAHEAD).get();
		Elements concertData = doc.getElementsByClass("b_c");

		for (Element el : concertData) {
			String url = el.attr("href");
			String name = el.getElementsByClass("b_c_b").first().text();
			String place = el.getElementsByClass("b_c_cp").first().text();
			String date = el.getElementsByClass("b_c_d").first().text();
			String city = place.split(" ")[0];
			String spot = place.substring(place.indexOf(" ") + 1);
			int day = Integer.valueOf(date.split(" ")[0]);
			String[] months = { "st", "lu", "mar", "kw", "maj", "cz", "lip", "si", "wr", "pa", "lis", "gr" };
			int month = 0;
			// Nie wierze że to jeszcze działa, lol. Moje dzieło takie piękne
			while (!date.split(" ")[1].startsWith(months[month]))
				month++;
			month++;
			int conYear = Integer.valueOf(date.split(" ")[2]);
			addConcert(name, city, spot, day, month, conYear, "GOAHEAD", url);
		}

	}

	@Override
	@Scheduled(fixedDelay = 550000)
	public void process() {
		try {
			log.info("Uruchamiam Go Ahead");
			workerActivityLogEntry(GoAhead.class.getSimpleName(), "1 AM every day");
			getData();
			log.info("Skończyłem");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
