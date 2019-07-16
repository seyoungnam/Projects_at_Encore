package view;

import controller.Controller;
import model.dto.Cover;
import model.dto.Ingredient;
import model.dto.MarketPrice;
import model.dto.ReadyToSell;

public class StartView {
	

	

	public static void main(String[] args) {
		
		//새로운 원료
		Ingredient ingredient5 = new Ingredient("밀키스", 60, "네덜란드");
		//새로운 포장재
		Cover cover5 = new Cover("알루미늄", 50, "브라질");
		//신규 제품 입고
		ReadyToSell product5 = new ReadyToSell("No0005", "청주", 180, ingredient5, cover5, 120);
		//제품 판매
		String sellId = "No0002";

		
		Controller controller = Controller.getInstance();
//		MarketPrice marketPrice = MarketPrice.getInstance();
		
		
		
		//현재 자판기 내에 존재하는 제품 전체 리스트 검색 + 손익 표시
		System.out.println("============= 1. 현재 자판기 내 판매가능 제품 목록 =============");
		controller.productListView();
		controller.CashCheck();
		
		System.out.println();
		
		//신규 제품 입고 + 손익 표시
		System.out.println("============= 2. 신규제품 입고 후 판매가능 제품 목록 =============");
		controller.insertProduct(product5);
		controller.productListView();
		controller.CashCheck();
		
		System.out.println();
		
		//제품 판매 + 손익 표시
		System.out.println("============= 3. 제품 판매 후 판매가능 제품 목록 =============");
		controller.deleteProduct("No0001");
		controller.productListView();
		controller.CashCheck();
		
		System.out.println();


	}

}
