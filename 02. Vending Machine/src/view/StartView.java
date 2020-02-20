package view;

import controller.Controller;
import model.dto.Cover;
import model.dto.Ingredient;
import model.dto.MarketPrice;
import model.dto.ReadyToSell;

public class StartView {
	

	

	public static void main(String[] args) {
		
		//���ο� ����
		Ingredient ingredient5 = new Ingredient("��Ű��", 60, "�״�����");
		//���ο� ������
		Cover cover5 = new Cover("�˷�̴�", 50, "�����");
		//�ű� ��ǰ �԰�
		ReadyToSell product5 = new ReadyToSell("No0005", "û��", 180, ingredient5, cover5, 120);
		//��ǰ �Ǹ�
		String sellId = "No0002";

		
		Controller controller = Controller.getInstance();
//		MarketPrice marketPrice = MarketPrice.getInstance();
		
		
		
		//���� ���Ǳ� ���� �����ϴ� ��ǰ ��ü ����Ʈ �˻� + ���� ǥ��
		System.out.println("============= 1. ���� ���Ǳ� �� �ǸŰ��� ��ǰ ��� =============");
		controller.productListView();
		controller.CashCheck();
		
		System.out.println();
		
		//�ű� ��ǰ �԰� + ���� ǥ��
		System.out.println("============= 2. �ű���ǰ �԰� �� �ǸŰ��� ��ǰ ��� =============");
		controller.insertProduct(product5);
		controller.productListView();
		controller.CashCheck();
		
		System.out.println();
		
		//��ǰ �Ǹ� + ���� ǥ��
		System.out.println("============= 3. ��ǰ �Ǹ� �� �ǸŰ��� ��ǰ ��� =============");
		controller.deleteProduct("No0001");
		controller.productListView();
		controller.CashCheck();
		
		System.out.println();


	}

}
