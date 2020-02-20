package encore.stocknews.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import encore.stocknews.exception.NotExistException;
import encore.stocknews.model.dto.NewsUser;
import encore.stocknews.model.dto.Stock;
import encore.stocknews.model.util.DBUtil;

public class NewsUserDAO {
	private static NewsUserDAO instance = new NewsUserDAO();

	private NewsUserDAO() {
	};

	public static NewsUserDAO getInstance() {
		return instance;
	}

	// Create (ADD)
	public boolean addNewsUser(NewsUser newsUser) throws Exception {
		boolean result = false;
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.flush();
		em.clear();
		try {
			em.persist(newsUser);
			tx.commit();
			result = true;
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		return result;
	}

	// Read (GET)
	public NewsUser getNewsUser(String id) throws Exception {
		NewsUser user = null;
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.flush();
		em.clear();
		try {
			user = em.find(NewsUser.class, id);
			if(user.getId() == null) {
				new NotExistException("존재하지 않는 유저 검색 시도");
			}
		} finally {
			em.close();
		}
		return user;
	}
	
	// Read All (GET)
	public List<NewsUser> getNewsUserList() throws Exception {
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<NewsUser> list = null;
		// JQPL : https://victorydntmd.tistory.com/205
		String jqpl = "select n from NewsUser n";
		tx.begin();
		em.flush();
		em.clear();
		try {
			list = em.createQuery(jqpl, NewsUser.class)
					.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
	
	// Update STOCK (By id)
	public boolean updateNewsUser(String id, Stock stock) throws Exception {
		boolean result = false;
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		NewsUser user = null;
		tx.begin();
		em.flush();
		em.clear();
		try {
			user = em.find(NewsUser.class, id);
			user.setStock(stock);
			em.persist(user);
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
	// Delete (By id)
	public boolean deleteNewsUser(String id) throws Exception {
		boolean result = false;
		EntityManagerFactory factory = DBUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.flush();
		em.clear();
		try {
			em.remove(em.find(NewsUser.class, id));
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
}
