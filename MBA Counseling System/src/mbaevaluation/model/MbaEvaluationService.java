package mbaevaluation.model;

import java.sql.SQLException;
import java.util.ArrayList;

import mbaevaluation.model.dto.RankingDTO;
import mbaevaluation.exception.NotExistException;
import mbaevaluation.model.dto.EducationDTO;
import mbaevaluation.model.dto.FinanceDTO;
import mbaevaluation.model.dto.JobOpportunityDTO;
 
 
 
 
 

public class MbaEvaluationService {

	
	private static MbaEvaluationService instance = new MbaEvaluationService();
	
	private MbaEvaluationService(){};
	public static MbaEvaluationService getInstance() {
		return instance;
	}

	// ��� �б� Ranking ������ ��ȯ
	public ArrayList<RankingDTO> getAllRanking() throws SQLException {
		return RankingDAO.getAllRanking();
	}
		

	// ��� �б� Education ������ ��ȯ
	public ArrayList<EducationDTO> getAllEducation() throws SQLException {
		return EducationDAO.getAllEducation();
	}
	

	// ��� �б� Finance ������ ��ȯ
	public ArrayList<FinanceDTO> getAllFinance() throws SQLException {
		return FinanceDAO.getAllFinance();
	}
	

	// ��� �б� Job Opportunity ������ ��ȯ
	public ArrayList<JobOpportunityDTO> getAllJobOpportunity() throws SQLException {
		return JobOpportunityDAO.getAllJobOpportunity();
	}
	

	// school id�� ranking �˻�
	public RankingDTO getRanking(String schoolId) throws SQLException, NotExistException {
		RankingDTO ranking = RankingDAO.getRanking(schoolId);
		if (ranking == null) {
			throw new NotExistException("�˻��Ͻ� ��ɱ�� ������ �����ϴ�.");
		}
		return ranking;
	}

	// school id�� education �˻�
	public EducationDTO getEducation(String schoolId) throws SQLException, NotExistException {
		EducationDTO education = EducationDAO.getEducation(schoolId);
		if (education == null) {
			throw new NotExistException("�˻��Ͻ� ��ɱ�� ������ �����ϴ�.");
		}
		return education;
	}

	// school id�� finance �˻�
	public FinanceDTO getFinance(String schoolId) throws SQLException, NotExistException {
		FinanceDTO finance = FinanceDAO.getFinance(schoolId);
		if (finance == null) {
			throw new NotExistException("�˻��Ͻ� ��ɱ�� ������ �����ϴ�.");
		}
		return finance;
	}	


	// school id�� Job Opportunity �˻�
	public JobOpportunityDTO getJobOpportunity(String stateId) throws SQLException, NotExistException {
		JobOpportunityDTO jobopportunity = JobOpportunityDAO.getJobOpportunity(stateId);
		if (jobopportunity == null) {
			throw new NotExistException("�˻��Ͻ� ��ɱ�� ������ �����ϴ�.");
		}
		return jobopportunity;
	}	
	
}
