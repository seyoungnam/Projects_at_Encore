package model.dto;

import lombok.Data;

@Data
public class Packaging {

	private String packId; //���� ��ǰ���� �ο��� ID
	private String facLocation; //��Ű¡ �۾��� ������ ���� ��ġ(�λ�/û��/����)
	private int packCost; //��Ű¡ �۾��� �ҿ�� ���
	private Ingredient ingredient; //�����(����) ���� �ٷ���
	private Cover cover; //�����(������) ���� �ٷ���
	
	public Packaging() {}
	public Packaging(String packId, String facLocation, int packCost,
					 Ingredient ingredient, Cover cover) {
		super();
		
		this.packId = packId;
		this.facLocation = facLocation;
		this.packCost = packCost;
		this.ingredient = ingredient;
		this.cover = cover;
	}
}
