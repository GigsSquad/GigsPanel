package it.coderunner.gigs.worker.backend.parser.songkick;

import it.coderunner.gigs.model.gig.Agency;
import it.coderunner.gigs.worker.backend.parser.ParserWorker;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;

import lombok.extern.log4j.Log4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class SongKick extends ParserWorker {

	private static final long serialVersionUID = -4030715416792184198L;

	// http://www.songkick.com/search?page=1&per_page=10&query=Spain&type=upcoming
	private static String url_1 = "http://www.songkick.com/search?page=";
	private static String url_2 = "&per_page=10&query=";
	private static String url_3 = "&type=upcoming";
	// url1+page+url2+country+url3
	private static ArrayList<String> monthNames = new ArrayList<String>(Arrays.asList(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
			"Oct", "Nov", "Dec" }));
	private static String[] countries = new String[] { "Poland" }; //TODO ,"Germany","France","Netherlands","Spain","Denmark","Italy","Sweden","Ireland","Hungary","England","Czech+Republic","Slovenia"

	@Override
	public void getData() throws IOException {
		for (String s : countries)
			getCountry(s);
	}

	private void getCountry(String country) throws IOException {
		System.out.println(url_1 + "1" + url_2 + country + url_3);
		Document doc = Jsoup.connect(url_1 + "1" + url_2 + country + url_3).timeout(100000).get();
		getConcerts(doc);
		if (doc.getElementsByClass("disabled").size() == 1) {
			int i = 2;
			do {
				try {
					doc = Jsoup.connect(url_1 + String.valueOf(i) + url_2 + country + url_3).timeout(100000).get();
					getConcerts(doc);
					i++;
				} catch (ConnectException e) {
					System.out.println("Exception caught");
					i = -1;
				}
			} while (doc.getElementsByClass("disabled").size() == 0);
		}
	}

	private void getConcerts(Document doc) throws IOException {
		for (Element el : doc.getElementsByClass("concert")) {
			int day = Integer.valueOf(el.getElementsByClass("date").get(1).text());
			int month = monthNames.indexOf(el.getElementsByClass("month").first().text()) + 1;
			int year = Integer.valueOf(el.getElementsByClass("year").first().text());
			String url = "http://www.songkick.com/" + el.getElementsByClass("subject").first().select("a").attr("href");
			String places = el.getElementsByClass("subject").first().text();
			String artist = el.getElementsByClass("subject").first().select("a").select("strong").text();
			String spot = spotCity(places)[0], city = spotCity(places)[1];
			addConcert(artist, city, spot, day, month, year, Agency.SONGKICK, url);
		}
	}

	private static String[] spotCity(String place) {
		String[] tmp = place.split(",");
		String[] spotArr = tmp[0].split(" ");
		String spot = spotArr[spotArr.length - 1];
		String city = tmp[1].trim();
		return new String[] { spot, city };
	}

	@Override
	@Scheduled(fixedDelay = 600000)
	public void process() {
		try {
			log.info("Uruchamiam SongKick");
			workerActivityLogEntry(SongKick.class.getSimpleName(), "Every hour of every day");
			getData();
			log.info("Skończyłem");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
