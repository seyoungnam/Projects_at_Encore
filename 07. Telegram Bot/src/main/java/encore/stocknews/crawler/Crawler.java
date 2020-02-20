package encore.stocknews.crawler;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler {

	protected static Document getDocFromUrl(String url) throws IOException {
		return Jsoup.connect(url).get();
	}

	protected static Document getDocFromUrl(String url, Map<String, String> inputData) throws IOException {
		return Jsoup.connect(url).data(inputData).get();
	}

	protected static String getStringFromEl(Element el, String query) {
		return el.select(query).text();
	}

	protected static String getStringFromEl(Element el, String query, String key) {
		return el.select(query).attr(key);
	}
	
	protected static int getIntFromEl(Element el, String query) {
		try {
			return Integer.parseInt(el.select(query).text().replace(",", ""));
		}catch (Exception er) {
			return 0;
		}
		
	}

	protected static int getIntFromEl(Element el, String query, String key) {
		try {
			return Integer.parseInt(el.select(query).attr(key).replace(",", ""));
		}catch (Exception er) {
			return 0;
		}
		
	}
}