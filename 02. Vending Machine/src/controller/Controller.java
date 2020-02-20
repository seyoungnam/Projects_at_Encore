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
	
	//��� ��� Ȯ��
	public void productListView() {
		ArrayList<ReadyToSell> productList = service.getAllProducts();
		if(productList.size() != 0) {
			System.out.println(productList); //EndView.projectListView(projectList);
		}else {
			System.out.println("���Ǳ� �� ��ǰ�� �����ϴ�."); //EndView.messageView("�������� ������Ʈ�� �������� �ʽ��ϴ�.");
		}
	}
	
	//Ư�� ��ǰ �˻�(Ȯ��)
	public void productView(String packId) {
		try {
			ReadyToSell product = service.getProduct2(packId);
			System.out.println(product); //EndView.projectView(project);
		} catch (NotExistException e) {
			System.out.println("failed - Ư�� ��ǰ �˻�(Ȯ��)"); //FailView.failMessageView(e.getMessage());
		}
	}
	
	//��� ���
	public void insertProduct(ReadyToSell newProduct) {
		service.insertProduct(newProduct);
		System.out.println();
	}

	//Ư�� ����� ��� ���� ����
	public void updateProduct(String packId, int deliveryCost) {
		try {
			service.updateProduct(packId, deliveryCost);
		} catch (NotExistException e) {
			System.out.println("falied - Ư�� ����� ��� ���� ����"); //FailView.failMessageView(e.getMessage());
			//e.printStackTrace();
		}	
	}
	
	//��ǰ �Ǹ�
	public void deleteProduct(String packId) {
		try {
			service.deleteProduct(packId);
		} catch (NotExistException e) {
			System.out.println("failed - ��ǰ �Ǹ�");//FailView.failMessageView(e.getMessage());
			//e.printStackTrace();
		}	
	}
	
	//���� Ȯ��
	public void CashCheck(){
		System.out.println("�ǸŸ��� :" + service.getCashInflow());
		System.out.println("������ :" + service.getCashOutflow());
	}
}
