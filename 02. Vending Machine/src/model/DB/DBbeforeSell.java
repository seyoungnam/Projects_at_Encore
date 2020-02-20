package model.DB;

import java.util.ArrayList;

import model.dto.Cover;
import model.dto.Ingredient;
import model.dto.Packaging;
import model.dto.ReadyToSell;

public class DBbeforeSell {
	private static DBbeforeSell instance = new DBbeforeSell();
	private ArrayList<ReadyToSell> productList = new ArrayList<ReadyToSell>(); 
	
	private DBbeforeSell() {
		productList.add(new ReadyToSell("No0001", "�λ�", 200, 
						new Ingredient("�ݶ�", 100, "�̱�"),
						new Cover("�˷�̴�", 40, "�����"),
						200));
		
		productList.add(new ReadyToSell("No0002", "û��", 150, 
				new Ingredient("���̴�", 50, "�ѱ�"),
				new Cover("ö", 60, "�ѱ�"),
				140));
		
		productList.add(new ReadyToSell("No0003", "����", 140, 
				new Ingredient("��Ű��", 100, "�״�����"),
				new Cover("�˷�̴�", 40, "�����"),
				60));
	}
	
	public static DBbeforeSell getInstance() {
		return instance;
	}
	public ArrayList<ReadyToSell> getProductList(){
		return productList;
	}
	public void insertProduct(ReadyToSell newProduct) {
		productList.add(newProduct);
	}

}
