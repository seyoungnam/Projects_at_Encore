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

	// 모든 학교 Ranking 데이터 반환
	public ArrayList<RankingDTO> getAllRanking() throws SQLException {
		return RankingDAO.getAllRanking();
	}
		

	// 모든 학교 Education 데이터 반환
	public ArrayList<EducationDTO> getAllEducation() throws SQLException {
		return EducationDAO.getAllEducation();
	}
	

	// 모든 학교 Finance 데이터 반환
	public ArrayList<FinanceDTO> getAllFinance() throws SQLException {
		return FinanceDAO.getAllFinance();
	}
	

	// 모든 학교 Job Opportunity 데이터 반환
	public ArrayList<JobOpportunityDTO> getAllJobOpportunity() throws SQLException {
		return JobOpportunityDAO.getAllJobOpportunity();
	}
	

	// school id로 ranking 검색
	public RankingDTO getRanking(String schoolId) throws SQLException, NotExistException {
		RankingDTO ranking = RankingDAO.getRanking(schoolId);
		if (ranking == null) {
			throw new NotExistException("검색하신 재능기부 정보가 없습니다.");
		}
		return ranking;
	}

	// school id로 education 검색
	public EducationDTO getEducation(String schoolId) throws SQLException, NotExistException {
		EducationDTO education = EducationDAO.getEducation(schoolId);
		if (education == null) {
			throw new NotExistException("검색하신 재능기부 정보가 없습니다.");
		}
		return education;
	}

	// school id로 finance 검색
	public FinanceDTO getFinance(String schoolId) throws SQLException, NotExistException {
		FinanceDTO finance = FinanceDAO.getFinance(schoolId);
		if (finance == null) {
			throw new NotExistException("검색하신 재능기부 정보가 없습니다.");
		}
		return finance;
	}	


	// school id로 Job Opportunity 검색
	public JobOpportunityDTO getJobOpportunity(String stateId) throws SQLException, NotExistException {
		JobOpportunityDTO jobopportunity = JobOpportunityDAO.getJobOpportunity(stateId);
		if (jobopportunity == null) {
			throw new NotExistException("검색하신 재능기부 정보가 없습니다.");
		}
		return jobopportunity;
	}	
	
}
