package encore.stocknews.crawler;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import encore.stocknews.model.dto.Stock;
import encore.stocknews.model.dto.StockNews;

public class StockCrawler extends Crawler {
	private static StockCrawler instance = new StockCrawler();
	private ArrayList<Stock> stockList = new ArrayList<>();
	private ArrayList<StockNews> stockNewsList = new ArrayList<>();

	private StockCrawler() {
	};

	public static StockCrawler getInstance() {
		return instance;
	}

	public ArrayList<Stock> getStockList() {
		return stockList;
	}

	public ArrayList<StockNews> getStockNewsList() {
		return stockNewsList;
	}

	public void crawling() throws IOException {
		Stock tmp = null;
		// url : https://finance.naver.com/sise/sise_market_sum.nhn
		// query 1 : :nth-child(2)
		Document doc = getDocFromUrl("https://finance.naver.com/sise/sise_market_sum.nhn");
		Elements els = doc.select("#contentarea > div.box_type_l > table.type_2 > tbody > tr");
		for (Element el : els) {
			if (el.text().length() == 0) {
				continue;
			} // 값이 비었다면 넘어감
			tmp = new Stock();
			tmp.setName(getStringFromEl(el, "td:nth-child(2)")); // 종목명
			tmp.setPrice(new BigDecimal(getIntFromEl(el, "td:nth-child(3)"))); // 현재가
			tmp.setNetUpDown(getStringFromEl(el, "td:nth-child(4) > img", "alt")); 
			tmp.setNet(new BigDecimal(getIntFromEl(el, "td:nth-child(4)"))); 
			tmp.setUpDown(getStringFromEl(el, "td:nth-child(5)")); 
			tmp.setMarketCap(new BigDecimal(getIntFromEl(el, "td:nth-child(7)"))); 
			stockList.add(tmp);
			System.out.println(tmp);
			newsUrlCrawling(tmp, "https://finance.naver.com" + getStringFromEl(el, "td:nth-child(2) > a", "href"));
			// #content > div.section.new_bbs > div.sub_section.news_section
//			return;
		}
	}

	public void newsUrlCrawling(Stock stock, String url) throws IOException {
		System.out.println(url);
		Document doc = getDocFromUrl(url);
		Elements els = doc.select("#content > div.section.new_bbs > div.sub_section.news_section").select("li");
		for (Element el : els) {
			if (el.text().length() == 0) {
				continue;
			}
			System.out.println("===");
			newsInfoCrawling(stock, "https://finance.naver.com" + getStringFromEl(el, "span > a:nth-child(1)", "href"));
//			return;
		}
	}
	

	public void newsInfoCrawling(Stock stock, String url) throws IOException {
		System.out.println(url);
		StockNews tmp = null;
		Document doc = getDocFromUrl(url);
		Elements els = doc.select("#content > div > table > tbody");
		for (Element el : els) {
			if (el.text().length() == 0) {
				continue;
			}
			System.out.println("---");
			tmp = new StockNews();
			tmp.setTitle(getStringFromEl(el, "tr > th > strong"));
			tmp.setUrl(url.replace("&sm=title_entity_id.basic", ""));
			tmp.setPubDate(getStringFromEl(el, "th > span > span"));
			tmp.setStock(stock);
			System.out.println(tmp);
			stockNewsList.add(tmp);
		}
	}
}