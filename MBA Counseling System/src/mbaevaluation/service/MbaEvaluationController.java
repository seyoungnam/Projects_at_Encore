package mbaevaluation.service;

import java.sql.SQLException;
import mbaevaluation.model.MbaEvaluationService;
import mbaevaluation.view.RunningEndView;
import mbaevaluation.exception.NotExistException;

public class MbaEvaluationController {

	private static MbaEvaluationController instance = new MbaEvaluationController();
	
	MbaEvaluationController(){}
	public static MbaEvaluationController getInstance() {
		return instance;
	}
	
	MbaEvaluationService service = MbaEvaluationService.getInstance();
	
	
	// 모든 학교 Ranking 검색 로직
	public void getAllRanking() {

		try {
			RunningEndView.rankingAllListView(service.getAllRanking());
			RunningEndView.sucessView();  
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
	
	// 모든 학교 Education 검색 로직
		public void getAllEducation() {

			try {
				RunningEndView.educationAllListView(service.getAllEducation());
				RunningEndView.sucessView();  
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	
	// 모든 학교 Finance 검색 로직
		public void getAllFinance() {
	
			try {
				RunningEndView.financeAllListView(service.getAllFinance());
				RunningEndView.sucessView();  
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}

	// 모든 학교 Job Opportunity 검색 로직
		public void getAllJobOportunity() {

			try {
				RunningEndView.jobopportunityAllListView(service.getAllJobOpportunity());
				RunningEndView.sucessView();  
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}				

	// 개별 학교 Raking 검색 로직		
		public void getRanking(String schoolId) {

			try {
				RunningEndView.allView(service.getRanking(schoolId));
				RunningEndView.sucessView();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NotExistException s) {
				s.printStackTrace();
			}
		}		
		
	// 개별 학교 Education 검색 로직		
		public void getEducation(String schoolId) {

			try {
				RunningEndView.allView(service.getEducation(schoolId));
				RunningEndView.sucessView();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NotExistException s) {
				s.printStackTrace();
			}
		}		

	// 개별 학교 Finance 검색 로직		
		public void getFinance(String schoolId) {

			try {
				RunningEndView.allView(service.getFinance(schoolId));
				RunningEndView.sucessView();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NotExistException s) {
				s.printStackTrace();
			}
		}			

	// 개별 학교 Job Opportunity 검색 로직		
		public void getJobOpportunity(String stateId) {

			try {
				RunningEndView.allView(service.getJobOpportunity(stateId));
				RunningEndView.sucessView();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NotExistException s) {
				s.printStackTrace();
			}
		}
		
	
	
}
