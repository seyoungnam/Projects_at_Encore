package encore.stocknews.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import encore.stocknews.model.dto.StockNews;
import encore.stocknews.model.util.DBUtil;

public class StockNewsDAO {
	private static StockNewsDAO instance = new StockNewsDAO();

	private StockNewsDAO() {
	};

	public static StockNewsDAO getInstance() {
		return instance;
	}

	// Create (ADD)
	public boolean addStockNewsList(ArrayList<StockNews> stockNewsList) throws Exception {
		boolean result = false;
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.flush();
		em.clear();
		try {
			for (StockNews stockNews : stockNewsList) {
				em.persist(stockNews);
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
	
	// Read All (GET)
	public List<StockNews> getStockNewsList(String name) throws Exception {
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<StockNews> list = null;
		// JQPL : https://victorydntmd.tistory.com/205
		String jqpl = "select s from StockNews s where stock_name = :v order by pubDate desc";
		tx.begin();
		em.flush();
		em.clear();
		try {
			list = em.createQuery(jqpl, StockNews.class)
					.setParameter("v", name)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return list;
	}
}
