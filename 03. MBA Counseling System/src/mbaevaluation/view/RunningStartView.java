package mbaevaluation.view;

import mbaevaluation.service.MbaEvaluationController;

public class RunningStartView {

	
	public static void main(String [] args){
		
		MbaEvaluationController controller = MbaEvaluationController.getInstance();
		
		// 모든 학교 Ranking 관련 데이터 검색
		controller.getAllRanking();
	 
		// 모든 학교 Education 관련 데이터 검색
		controller.getAllEducation();
		
		// 모든 학교 Finance 관련 데이터 검색
		controller.getAllFinance();
		
		// 모든 학교 Job Opportunity 관련 데이터 검색
		controller.getAllJobOportunity();
				
		
		// 특정 학교 Ranking 관련 데이터 검색
		controller.getRanking("243744");
		
		// 특정 학교 Education 관련 데이터 검색
		controller.getEducation("243744");
		
		// 특정 학교 Finance 관련 데이터 검색
		controller.getFinance("243744");
		
		// 특정 학교 Job Opportunity 관련 데이터 검색
		controller.getJobOpportunity("243744");
	 
	}
	
}
