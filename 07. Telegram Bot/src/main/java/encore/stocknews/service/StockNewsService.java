package encore.stocknews.service;

import java.util.ArrayList;
import java.util.List;

import encore.stocknews.crawler.StockCrawler;
import encore.stocknews.model.NewsUserDAO;
import encore.stocknews.model.StockDAO;
import encore.stocknews.model.StockNewsDAO;
import encore.stocknews.model.dto.NewsUser;
import encore.stocknews.model.dto.Stock;
import encore.stocknews.model.dto.StockNews;

public class StockNewsService {
	private static StockNewsService instance = new StockNewsService();

	private StockNewsService() {};
	
	public static StockNewsService getInstance() {
		return instance;
	}

	// Stock
	// C List
	public boolean addStockList(ArrayList<Stock> stockList) throws Exception {
		StockDAO stockDAO = StockDAO.getInstance();
		return stockDAO.addStockList(stockList);
	}

	// R (By name)
	public Stock getStock(String name) throws Exception {
		StockDAO stockDAO = StockDAO.getInstance();
		return stockDAO.getStock(name);
	}
	
	// R List(All)
	public List<Stock> getStockList() throws Exception {
		StockDAO stockDAO = StockDAO.getInstance();
		return stockDAO.getStockList();
	}

	// StockNews
	// C List
	public boolean addStockNewsList(ArrayList<StockNews> stockNewsList) throws Exception {
		StockNewsDAO stockNewsDAO = StockNewsDAO.getInstance();
		return stockNewsDAO.addStockNewsList(stockNewsList);
	}

	// R List (By name)
	public List<StockNews> getStockNewsList(String name) throws Exception {
		StockNewsDAO stockNewsDAO = StockNewsDAO.getInstance();
		return stockNewsDAO.getStockNewsList(name);
	}

	// User
	// C
	public boolean addNewsUser(NewsUser newsUser, String stockName) throws Exception {
		NewsUserDAO newsUserDAO = NewsUserDAO.getInstance();
		newsUser.setStock(getStock(stockName));
		return newsUserDAO.addNewsUser(newsUser);
	}

	// R List(All)
	public List<NewsUser> getNewsUserList() throws Exception {
		NewsUserDAO newsUserDAO = NewsUserDAO.getInstance();
		return newsUserDAO.getNewsUserList();
	}

	// R (By id)
	public NewsUser getNewsUser(String id) throws Exception {
		NewsUserDAO newsUserDAO = NewsUserDAO.getInstance();
		return newsUserDAO.getNewsUser(id);
	}

	// U Stock (By id)
	public boolean updateNewsUser(String id, Stock stock) throws Exception {
		NewsUserDAO newsUserDAO = NewsUserDAO.getInstance();
		return newsUserDAO.updateNewsUser(id, stock);
	}

	// D (By id)
	public boolean deleteNewsUser(String id) throws Exception {
		NewsUserDAO newsUserDAO = NewsUserDAO.getInstance();
		return newsUserDAO.deleteNewsUser(id);
	}

	public void crawling() {
		StockCrawler crawler = StockCrawler.getInstance();
		try {
//			System.out.println("크롤링 시작");
			crawler.crawling();
//			System.out.println("크롤링 완료");
//			System.out.println("DB 적재 - Stock");
			addStockList(crawler.getStockList());
//			System.out.println("DB 완료");
//			System.out.println("DB 적재 - StockNews");
			addStockNewsList(crawler.getStockNewsList());
//			System.out.println("DB 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		DBUtil.runFactory();
//		/*
//		 * String name = "LG"; for(StockNews v : getStockNewsList(name)) {
//		 * System.out.println(v); }
//		 */
////		System.out.println(getStock("삼성전자"));
//		NewsUser newsUser = null;
//		// 전체 유저 검색
//		System.out.println("==========");
//		System.out.println("<전체 유저 검색>");
//		for (NewsUser user : getNewsUserList()) {
//			System.out.println(user);
//		}
//		// 유저 생성
//		System.out.println("==========");
//		System.out.println("<유저 생성>");
//		newsUser = new NewsUser();
//		newsUser.setId("jim");
//		newsUser.setName("짐 스턴");
//		newsUser.setPw("st");
//		newsUser.setStock(getStock("넷마블"));
//		if (addNewsUser(newsUser)) {
//			System.out.println("<<추가 성공>>");
//		} else {
//			System.out.println("<<추가 실패>>");
//		}
//		// 전체 유저 검색
//		System.out.println("==========");
//		System.out.println("<전체 유저 검색>");
//		for (NewsUser v : getNewsUserList()) {
//			System.out.println(v);
//		}
//		// 유저 검색 + 기사 검색
//		System.out.println("==========");
//		System.out.println("<유저 검색 + 기사 검색>");
//		newsUser = getNewsUser("jim");
//		System.out.println(newsUser);
//		try {
//			for (StockNews v : getStockNewsList(newsUser.getStock().getName())) {
//				System.out.println(v);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// 유저 수정
//		System.out.println("==========");
//		System.out.println("<유저 수정>");
//		if (updateNewsUser("jim", getStock("강원랜드"))) {
//			System.out.println("<<수정 성공>>");
//		} else {
//			System.out.println("<<수정 실패>>");
//		}
//		// 유저 검색 + 기사 검색
//		System.out.println("==========");
//		System.out.println("<유저 검색 + 기사 검색>");
//		newsUser = getNewsUser("jim");
//		System.out.println(newsUser);
//		try {
//			for (StockNews v : getStockNewsList(newsUser.getStock().getName())) {
//				System.out.println(v);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// 유저 삭제
//		System.out.println("==========");
//		System.out.println("<유저 삭제>");
//		if (deleteNewsUser("jim")) {
//			System.out.println("<<삭제 성공>>");
//		} else {
//			System.out.println("<<삭제 실패>>");
//		}
//		// 전체 유저 검색
//		System.out.println("==========");
//		System.out.println("<전체 유저 검색>");
//		for (NewsUser user : getNewsUserList()) {
//			System.out.println(user);
//		}
//		// 유저 검색 + 기사 검색
//		System.out.println("==========");
//		System.out.println("<유저 검색 + 기사 검색>");
//		newsUser = getNewsUser("jim");
//		System.out.println(newsUser);
//		try {
//			for (StockNews v : getStockNewsList(newsUser.getStock().getName())) {
//				System.out.println(v);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		DBUtil.closeFactory();
//	}

}
