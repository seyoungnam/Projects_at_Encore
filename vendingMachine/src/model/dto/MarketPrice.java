package model.dto;

import lombok.Data;

@Data
public class MarketPrice {
	
	private static MarketPrice instance = new MarketPrice();
	
	
	private String productType;
	private int sellPrice;
	
	public MarketPrice() {}
	public MarketPrice(String productType, int sellPrice) {
		this.productType = productType;
		this.sellPrice = sellPrice;
	}
	public static MarketPrice getInstance() {
		return instance;
	}

}
