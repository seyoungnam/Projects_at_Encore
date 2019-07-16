package model.dto;

import lombok.Data;

@Data
public class Packaging {

	private String packId; //개별 제품별로 부여된 ID
	private String facLocation; //패키징 작업을 수행한 공장 위치(부산/청주/파주)
	private int packCost; //패키징 작업에 소요된 비용
	private Ingredient ingredient; //원재료(원료) 정보 꾸러미
	private Cover cover; //원재료(포장재) 정보 꾸러미
	
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
