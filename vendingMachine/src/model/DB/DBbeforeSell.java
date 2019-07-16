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
		productList.add(new ReadyToSell("No0001", "부산", 200, 
						new Ingredient("콜라", 100, "미국"),
						new Cover("알루미늄", 40, "브라질"),
						200));
		
		productList.add(new ReadyToSell("No0002", "청주", 150, 
				new Ingredient("사이다", 50, "한국"),
				new Cover("철", 60, "한국"),
				140));
		
		productList.add(new ReadyToSell("No0003", "파주", 140, 
				new Ingredient("밀키스", 100, "네덜란드"),
				new Cover("알루미늄", 40, "브라질"),
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
