package encore.stocknews.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import encore.stocknews.exception.NotExistException;
import encore.stocknews.model.dto.NewsUser;
import encore.stocknews.model.dto.Stock;
import encore.stocknews.model.util.DBUtil;

public class StockDAO {
	private static StockDAO instance = new StockDAO();

	private StockDAO() {
	};

	public static StockDAO getInstance() {
		return instance;
	}

	// Create (ADD)
	public boolean addStockList(ArrayList<Stock> stockList) throws Exception {
		boolean result = false;
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.flush();
		em.clear();
		try {
			for (Stock stock : stockList) {
				em.persist(stock);
			}
			tx.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return result;
	}

	// Read (GET)
	public Stock getStock(String name) throws Exception {
		Stock stock = null;
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.flush();
		em.clear();
		try {
			stock = em.find(Stock.class, name);
			if (stock == null) {
				throw new NotExistException("존재하지 않는 종목 검색 시도");
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return stock;
	}

	// Read All (GET)
	public List<Stock> getStockList() throws Exception {
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Stock> list = null;
		// JQPL : https://victorydntmd.tistory.com/205
		String jqpl = "select s from Stock s";
		tx.begin();
		em.flush();
		em.clear();
		try {
			list = em.createQuery(jqpl, Stock.class).getResultList();
		} finally {
			em.close();
		}
		return list;
	}
}