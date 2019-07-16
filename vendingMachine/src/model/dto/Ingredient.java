package model.dto;

import lombok.Data;

@Data
public class Ingredient {

	private String ingreType; //원재료명(콜라/사이다/밀키스)
	private int ingreCost; //원재료 원가
	private String ingreOrigin; //원산지(미국/네덜란드/한국)
	
	public Ingredient() {}
	public Ingredient(String ingreType, int ingreCost, String ingreOrigin) {
		super();

		this.ingreType = ingreType;
		this.ingreCost = ingreCost;
		this.ingreOrigin = ingreOrigin;
	}
}
