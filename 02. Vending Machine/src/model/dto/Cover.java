package model.dto;

import lombok.Data;

@Data
public class Cover {

	private String coverType; //포장재 종류(철/알루미늄)
	private int coverCost; //포장재 원가
	private String coverOrigin; //포장재 원산지(중국/브라질/한국)
	
	public Cover() {}
	public Cover(String coverType, int coverCost, String coverOrigin) {
		super();
		this.coverType = coverType;
		this.coverCost = coverCost;
		this.coverOrigin = coverOrigin;
	}
}
