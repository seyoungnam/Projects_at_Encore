package mbaevaluation.view;

import java.util.ArrayList;

import mbaevaluation.model.dto.EducationDTO;
import mbaevaluation.model.dto.FinanceDTO;
import mbaevaluation.model.dto.JobOpportunityDTO;
import mbaevaluation.model.dto.RankingDTO;

public class RunningEndView {

	// 모든 학교 Ranking 관련 데이터 출력
	
	public static void rankingAllListView(ArrayList<RankingDTO> allRanking) {
		System.out.println("\n***** 모든 학교 ranking 검색 *****");

		int length = allRanking.size();

		if (length != 0) {
			for (int index = 0; index < length; index++) {
				System.out.println("검색정보 " + (index + 1) + " - " + allRanking.get(index));
			}
		}
	}
	
	// 모든 학교 Education 관련 데이터 출력
	
	public static void educationAllListView(ArrayList<EducationDTO> allEducation) {
		System.out.println("\n***** 모든 학교 education 검색 *****");

		int length = allEducation.size();

		if (length != 0) {
			for (int index = 0; index < length; index++) {
				System.out.println("검색정보 " + (index + 1) + " - " + allEducation.get(index));
			}
		}
	}	 
	
	// 모든 학교 Job Opportunity 관련 데이터 출력
	
	public static void jobopportunityAllListView(ArrayList<JobOpportunityDTO> allJobOpportunity) {
		System.out.println("\n***** 모든 학교 job opportunity 검색 *****");

		int length = allJobOpportunity.size();

		if (length != 0) {
			for (int index = 0; index < length; index++) {
				System.out.println("검색정보 " + (index + 1) + " - " + allJobOpportunity.get(index));
			}
		}
	}
	
	 
	// 모든 학교 Finance 관련 데이터 출력
	
	public static void financeAllListView(ArrayList<FinanceDTO> allFinance) {
		System.out.println("\n***** 모든 학교 finance 검색 *****");

		int length = allFinance.size();

		if (length != 0) {
			for (int index = 0; index < length; index++) {
				System.out.println("검색정보 " + (index + 1) + " - " + allFinance.get(index));
			}
		}
	}
	 
	 
	// 특정 학교 Ranking 출력
	public static void rankingView(RankingDTO ranking) {
		System.out.println(ranking);
	}

	// 특정 학교 Education 출력
	public static void educationView(EducationDTO education) {
		System.out.println(education);
	}
	
	// 특정 학교 Job Opportunity 출력
	public static void jobopportunityView(JobOpportunityDTO jobopportunity) {
		System.out.println(jobopportunity);
	}
	
	// 특정 학교 Finance 출력
	public static void financeView(FinanceDTO finance) {
		System.out.println(finance);
	}
	
	// 모든 DTO 정보 출력하는 메소드
	public static void allView(Object object) {
		System.out.println("\n***** 특정 정보 검색 *****");
		System.out.println(object);
	}

	// 예외 상황 출력
	public static void showError(String msg) {
		System.out.println(msg);
	}

	// 성공 메시지
	public static void sucessView() {
		System.out.println("성공적으로 검색되었습니다.");
	}
}
