package encore.stocknews.view;

import java.util.List;

import encore.stocknews.model.dto.NewsUser;
import encore.stocknews.model.dto.Stock;
import encore.stocknews.model.dto.StockNews;

public class EndView {
	
	public static void newsUserListView(List<NewsUser> newsUserList) {
		for(NewsUser user : newsUserList) {
			System.out.println(user);
		}
	}
	
	public static void stockView(Stock stock) {
		System.out.println(stock);
	}
	
	public static void stockListView(List<Stock> stockList) {
		for(Stock stock : stockList) {
			System.out.println(stock);
		}
	}

	public static void showSuccess(String message) {
		System.out.println(message);
	}

	public static void newsUserView(NewsUser newsUser) {
		System.out.println(newsUser);
	}

	public static void stockNewsListView(List<StockNews> list) {
		for(StockNews news : list) {
			System.out.println(news);
		}
	}	
	
}
