package model.dto;

import lombok.Data;

@Data
public class ReadyToSell extends Packaging {

	private int deliveryCost; // ��ۺ�
	
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
		builder.append(" ��ǰid :");
		builder.append(super.getPackId());
		builder.append("\t ����� ���� :");
		builder.append(super.getIngredient().getIngreCost());
		builder.append("\t ���� ���� :");
		builder.append(super.getCover().getCoverCost());
		builder.append("\t ��Ű¡ ���� :");
		builder.append(super.getPackCost());
		builder.append("\t ��� ���� : ");
		builder.append(deliveryCost);
		builder.append("\t ���� ����:");
		builder.append(super.getIngredient().getIngreType());
		builder.append("\t ������ ����:");
		builder.append(super.getCover().getCoverType());
		builder.append("\t ���� ������:");
		builder.append(super.getIngredient().getIngreOrigin());
		builder.append("\t ������ ������:");
		builder.append(super.getCover().getCoverOrigin());
		builder.append("\n");
		return builder.toString(); 
	}
}
