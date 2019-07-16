package model.dto;

import lombok.Data;

@Data
public class ReadyToSell extends Packaging {

	private int deliveryCost; // 배송비
	
	public ReadyToSell() {}
	public ReadyToSell(int deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	public ReadyToSell(String packId, String facLocation, int packCost, Ingredient ingredient, Cover cover, int deliveryCost) {
		super(packId, facLocation, packCost, ingredient, cover);
		this.deliveryCost = deliveryCost;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 제품id :");
		builder.append(super.getPackId());
		builder.append("\t 원재료 원가 :");
		builder.append(super.getIngredient().getIngreCost());
		builder.append("\t 포장 원가 :");
		builder.append(super.getCover().getCoverCost());
		builder.append("\t 패키징 원가 :");
		builder.append(super.getPackCost());
		builder.append("\t 배송 원가 : ");
		builder.append(deliveryCost);
		builder.append("\t 원료 종류:");
		builder.append(super.getIngredient().getIngreType());
		builder.append("\t 포장재 종류:");
		builder.append(super.getCover().getCoverType());
		builder.append("\t 원료 원산지:");
		builder.append(super.getIngredient().getIngreOrigin());
		builder.append("\t 포장재 원산지:");
		builder.append(super.getCover().getCoverOrigin());
		builder.append("\n");
		return builder.toString(); 
	}
}
