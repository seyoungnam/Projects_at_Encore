package model.DB;

import java.util.ArrayList;

import model.dto.Cover;
import model.dto.Ingredient;
import model.dto.MarketPrice;
import model.dto.ReadyToSell;

public class DBafterSell {
	private static DBafterSell instance = new DBafterSell();
	private ArrayList<ReadyToSell> soldProductList = new ArrayList<ReadyToSell>();
	
	
	private DBafterSell() {
//		soldProductList.add(new ReadyToSell("No0004", "û��", 170, 
//				new Ingredient("���̴�", 200, "�̱�"),
//				new Cover("ö", 60, "�ѱ�"),
//				140));
	}
	
	public static DBafterSell getInstance() {
		return instance;
	}
	public ArrayList<ReadyToSell> getProductList(){
		return soldProductList;
	}
	public void insertProduct(ReadyToSell newProduct) {
		soldProductList.add(newProduct);
	}
}

