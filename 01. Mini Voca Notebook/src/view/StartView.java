package view;

import controller.DictController;
import model.InOut;

public class StartView {
	
	public static void mainMenu () {
		System.out.println("==== �������� Voca Loca ====");		
		System.out.println("1. �ܾ��� �ҷ�����");
		System.out.println("2. ���� �ܾ� �˻�");
		System.out.println("3. �ű� �ܾ� �Է�");
		System.out.println("4. �ܾ� �ǹ� ����");
		System.out.println("5. ���� �ܾ� ����");
		System.out.println("==========================");
		System.out.println("���Ͻô� �޴� ��ȣ�� ������ �ּ���.");
		
		DictController.request(InOut.intInOut());
	}

	public static void main(String[] args) {
		System.out.println("�������� Voca Loca�� ���� ���� ȯ���մϴ�.");
		System.out.println("ID�� �Է��ϼ���: ");
		//id �Է��ϰ� �α���
		String realId = "nsy";
		if(!realId.equals(InOut.strInOut())) {
			System.out.println("���� ����, �ٸ� ID�� �Է��ϼ���.");
		}else {
			StartView.mainMenu();
		}
	}
}
