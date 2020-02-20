package model.dto;

import lombok.Data;

@Data
public class Ingredient {

	private String ingreType; //������(�ݶ�/���̴�/��Ű��)
	private int ingreCost; //����� ����
	private String ingreOrigin; //������(�̱�/�״�����/�ѱ�)
	
	public Ingredient() {}
	public Ingredient(String ingreType, int ingreCost, String ingreOrigin) {
		super();

		this.ingreType = ingreType;
		this.ingreCost = ingreCost;
		this.ingreOrigin = ingreOrigin;
	}
}
