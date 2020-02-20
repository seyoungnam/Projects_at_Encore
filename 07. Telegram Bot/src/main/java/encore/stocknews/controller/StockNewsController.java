package encore.stocknews.controller;

import java.util.List;

import encore.stocknews.model.dto.NewsUser;
import encore.stocknews.model.dto.Stock;
import encore.stocknews.model.dto.StockNews;
import encore.stocknews.service.StockNewsService;
import encore.stocknews.view.EndView;
import encore.stocknews.view.FailView;

public class StockNewsController {
	private static StockNewsController instance = new StockNewsController();
	private static StockNewsService service = StockNewsService.getInstance();

	private StockNewsController() {
	};

	public static StockNewsController getInstance() {
		return instance;
	}

	public void getNewsUserList() {
		try {
			EndView.newsUserListView(service.getNewsUserList());
		} catch (Exception e) {
			e.printStackTrace();
			FailView.showError("유저 전체 검색 시 에러 발생");
		}
	}

	public void crawling() {
		service.crawling();
	}

	public void addNewsUser(NewsUser newsUser, String stockName) {
		try {
			if (service.addNewsUser(newsUser, stockName)) {
				EndView.showSuccess("유저 추가 성공");
			} else {
				FailView.showError("유저 추가 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FailView.showError("유저 추가 시 에러 발생");
		}
	}

	public void getNewsUser(String id) {
		try {
			EndView.newsUserView(service.getNewsUser(id));
		} catch (Exception e) {
			e.printStackTrace();
			FailView.showError("유저 검색 시 에러 발생");
		}
	}

	public void updateNewsUser(String id, String stockName) {
		try {
			if (service.updateNewsUser(id, service.getStock(stockName))) {
				EndView.showSuccess("유저 수정 성공");
			} else {
				FailView.showError("유저 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FailView.showError("유저 수정 시 에러 발생");
		}
	}

	public void deleteNewsUser(String id) {
		try {
			if (service.deleteNewsUser(id)) {
				EndView.showSuccess("유저 삭제 성공");
			} else {
				FailView.showError("유저 삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FailView.showError("유저 수정 시 에러 발생");
		}
	}

	public void sendStockNewsList(String id) {
		try {
			NewsUser user = service.getNewsUser(id);
			List<StockNews> list = service.getStockNewsList(user.getStock().getName());
			EndView.stockNewsListView(list);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.showError("뉴스 리스트 발송 시 에러 발생");
		}
	}

	public void getStock(String name) {
		try {
			Stock stock = service.getStock(name);
			EndView.stockView(stock);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.showError("한 종목 검색 시 에러 발생");
		}
	}

	public void getStockList() {
		try {
			List<Stock> list = service.getStockList();
			EndView.stockListView(list);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.showError("모든 종목 검색 시 에러 발생");
		}
	}

}
