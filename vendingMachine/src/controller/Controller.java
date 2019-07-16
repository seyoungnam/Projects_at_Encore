package controller;

import java.util.ArrayList;

import exception.NotExistException;
import model.dto.ReadyToSell;

public class Controller {
	
	private static Controller instance = new Controller();
	private Service service = Service.getInstance();
	
	private Controller() {}
	
	public static Controller getInstance() {
		return instance;
	}
	
	//모든 재고 확인
	public void productListView() {
		ArrayList<ReadyToSell> productList = service.getAllProducts();
		if(productList.size() != 0) {
			System.out.println(productList); //EndView.projectListView(projectList);
		}else {
			System.out.println("자판기 내 제품이 없습니다."); //EndView.messageView("진행중인 프로젝트는 존재하지 않습니다.");
		}
	}
	
	//특정 제품 검색(확인)
	public void productView(String packId) {
		try {
			ReadyToSell product = service.getProduct2(packId);
			System.out.println(product); //EndView.projectView(project);
		} catch (NotExistException e) {
			System.out.println("failed - 특정 제품 검색(확인)"); //FailView.failMessageView(e.getMessage());
		}
	}
	
	//재고 상승
	public void insertProduct(ReadyToSell newProduct) {
		service.insertProduct(newProduct);
		System.out.println();
	}

	//특정 재고의 배송 원가 수정
	public void updateProduct(String packId, int deliveryCost) {
		try {
			service.updateProduct(packId, deliveryCost);
		} catch (NotExistException e) {
			System.out.println("falied - 특정 재고의 배송 원가 수정"); //FailView.failMessageView(e.getMessage());
			//e.printStackTrace();
		}	
	}
	
	//제품 판매
	public void deleteProduct(String packId) {
		try {
			service.deleteProduct(packId);
		} catch (NotExistException e) {
			System.out.println("failed - 제품 판매");//FailView.failMessageView(e.getMessage());
			//e.printStackTrace();
		}	
	}
	
	//손익 확인
	public void CashCheck(){
		System.out.println("판매매출 :" + service.getCashInflow());
		System.out.println("재고원가 :" + service.getCashOutflow());
	}
}
