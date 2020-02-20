package controller;

import model.DictModel;
import model.InOut;
import view.EndView;
import view.StartView;


public class DictController {
	public static void request(int reqNumber) {
		
		if(reqNumber == 1) {
			System.out.println("====== �ܾ���  ======");
			EndView.printAll(DictModel.getAll());	
			StartView.mainMenu();
		
		}else if(reqNumber ==2) {
			System.out.println("====== �ܾ� �˻� ======");
			System.out.println("�˻��ϰ��� �ϴ� ���ܾ �Է��ϼ���.");
			boolean t = DictModel.search(InOut.engInOut());
				if(t) {
					EndView.printSuccess("�˻��Ǿ����ϴ�.");
				}else {
					EndView.printFail("�Է��Ͻ� �ܾ�� �ܾ��忡 �����ϴ�.");
				}
			StartView.mainMenu();
			
		}else if(reqNumber ==3) {
			System.out.println("====== �ܾ� �Է� ======");
			System.out.println("�ű� ���ܾ �Է��ϼ���.");
			String newVoca = InOut.engInOut();
			System.out.println("�ش� �ܾ��� �ǹ̸� �Է��ϼ���.");
			String newMean = InOut.korInOut();
			System.out.println("���� ��¥�� �Է��ϼ���(����:YYYYMMDD).");
			int newDate = InOut.dateInOut();
			DictModel.insert(newVoca, newMean, newDate);
			/*	if(t) {
					EndView.printSuccess("���ܾ ���� �Ǿ����ϴ�.");
				}else {
					EndView.printFail("���ܾ� ���忡 �����Ͽ����ϴ�.");
				} */
			StartView.mainMenu();
			
		}else if(reqNumber ==4) {
			System.out.println("====== �ǹ� ���� ======");
			EndView.printAll(DictModel.getAll());
			System.out.println("�����Ͻ� ���ܾ �Է��ϼ���.");
			String newVoca = InOut.engInOut();
			System.out.println("���ϴ� �ǹ̸� �Է��ϼ���.");
			String newMean = InOut.korInOut();
			System.out.println("���� ��¥�� �Է��ϼ���(����:YYYYMMDD).");
			int newDate = InOut.dateInOut();
			boolean t = DictModel.update(newVoca, newMean, newDate);
				if(t) {
					EndView.printSuccess("���ܾ �����Ǿ����ϴ�.");
				}else {
					EndView.printFail("�ش� ���ܾ �ܾ��忡 �����ϴ�.");
				}
			StartView.mainMenu();
			
		}else if(reqNumber ==5) {
			System.out.println("====== �ܾ� ���� ======");
			EndView.printAll(DictModel.getAll());	
			System.out.println("�����Ͻ� ���ܾ �Է��ϼ���.");
			boolean t = DictModel.delete(InOut.strInOut());
			if (t) {
				EndView.printSuccess("�ش� ���ܾ �ܾ��忡�� ���� �Ǿ����ϴ�.");
			}else {
				EndView.printFail("�ܾ��� ���� �ִ� ���ܾ� ������  �����Ͽ����ϴ�.");
			}
			StartView.mainMenu();
			
		}else {
			EndView.printFail("��û�Ͻ� ������ ���� �� �� �����ϴ�.");
			StartView.mainMenu();
		}	
	}

}
	