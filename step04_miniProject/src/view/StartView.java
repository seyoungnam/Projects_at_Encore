package view;

import controller.DictController;
import model.InOut;

public class StartView {
	
	public static void mainMenu () {
		System.out.println("==== 남세영의 Voca Loca ====");		
		System.out.println("1. 단어장 불러오기");
		System.out.println("2. 개별 단어 검색");
		System.out.println("3. 신규 단어 입력");
		System.out.println("4. 단어 의미 수정");
		System.out.println("5. 기존 단어 삭제");
		System.out.println("==========================");
		System.out.println("원하시는 메뉴 번호를 선택해 주세요.");
		
		DictController.request(InOut.intInOut());
	}

	public static void main(String[] args) {
		System.out.println("남세영의 Voca Loca에 오신 것을 환영합니다.");
		System.out.println("ID를 입력하세요: ");
		//id 입력하고 로그인
		String realId = "nsy";
		if(!realId.equals(InOut.strInOut())) {
			System.out.println("접속 실패, 다른 ID를 입력하세요.");
		}else {
			StartView.mainMenu();
		}
	}
}
