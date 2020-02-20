package controller;

import model.DictModel;
import model.InOut;
import view.EndView;
import view.StartView;


public class DictController {
	public static void request(int reqNumber) {
		
		if(reqNumber == 1) {
			System.out.println("====== 단어장  ======");
			EndView.printAll(DictModel.getAll());	
			StartView.mainMenu();
		
		}else if(reqNumber ==2) {
			System.out.println("====== 단어 검색 ======");
			System.out.println("검색하고자 하는 영단어를 입력하세요.");
			boolean t = DictModel.search(InOut.engInOut());
				if(t) {
					EndView.printSuccess("검색되었습니다.");
				}else {
					EndView.printFail("입력하신 단어는 단어장에 없습니다.");
				}
			StartView.mainMenu();
			
		}else if(reqNumber ==3) {
			System.out.println("====== 단어 입력 ======");
			System.out.println("신규 영단어를 입력하세요.");
			String newVoca = InOut.engInOut();
			System.out.println("해당 단어의 의미를 입력하세요.");
			String newMean = InOut.korInOut();
			System.out.println("금일 날짜를 입력하세요(형식:YYYYMMDD).");
			int newDate = InOut.dateInOut();
			DictModel.insert(newVoca, newMean, newDate);
			/*	if(t) {
					EndView.printSuccess("새단어가 저장 되었습니다.");
				}else {
					EndView.printFail("새단어 저장에 실패하였습니다.");
				} */
			StartView.mainMenu();
			
		}else if(reqNumber ==4) {
			System.out.println("====== 의미 수정 ======");
			EndView.printAll(DictModel.getAll());
			System.out.println("수정하실 영단어를 입력하세요.");
			String newVoca = InOut.engInOut();
			System.out.println("원하는 의미를 입력하세요.");
			String newMean = InOut.korInOut();
			System.out.println("금일 날짜를 입력하세요(형식:YYYYMMDD).");
			int newDate = InOut.dateInOut();
			boolean t = DictModel.update(newVoca, newMean, newDate);
				if(t) {
					EndView.printSuccess("영단어가 수정되었습니다.");
				}else {
					EndView.printFail("해당 영단어가 단어장에 없습니다.");
				}
			StartView.mainMenu();
			
		}else if(reqNumber ==5) {
			System.out.println("====== 단어 삭제 ======");
			EndView.printAll(DictModel.getAll());	
			System.out.println("삭제하실 영단어를 입력하세요.");
			boolean t = DictModel.delete(InOut.strInOut());
			if (t) {
				EndView.printSuccess("해당 영단어가 단어장에서 삭제 되었습니다.");
			}else {
				EndView.printFail("단어장 내에 있는 영단어 삭제를  실패하였습니다.");
			}
			StartView.mainMenu();
			
		}else {
			EndView.printFail("요청하신 정보는 서비스 할 수 없습니다.");
			StartView.mainMenu();
		}	
	}

}
	