package model.DB;

import java.util.ArrayList;

import model.dto.MarketPrice;

public class DBMarketPrice {
	private static DBMarketPrice instance = new DBMarketPrice();
	private ArrayList<MarketPrice> marketPriceList = new ArrayList<MarketPrice>();
	
	private DBMarketPrice() {
		marketPriceList.add(new MarketPrice("�ݶ�", 1500));
		marketPriceList.add(new MarketPrice("���̴�", 1700));
		marketPriceList.add(new MarketPrice("��Ű��", 1100));	
	}
	
	public static DBMarketPrice getInstance() {
		return instance;
	}
	public ArrayList<MarketPrice> getMarketPriceList(){
		return marketPriceList;
	}
	public void insertProduct(MarketPrice newPrice) {
		marketPriceList.add(newPrice);
	}

}
