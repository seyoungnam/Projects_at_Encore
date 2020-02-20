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
//		soldProductList.add(new ReadyToSell("No0004", "청주", 170, 
//				new Ingredient("사이다", 200, "미국"),
//				new Cover("철", 60, "한국"),
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

