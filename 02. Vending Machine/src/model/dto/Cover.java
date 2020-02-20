package model.dto;

import lombok.Data;

@Data
public class Cover {

	private String coverType; //������ ����(ö/�˷�̴�)
	private int coverCost; //������ ����
	private String coverOrigin; //������ ������(�߱�/�����/�ѱ�)
	
	public Cover() {}
	public Cover(String coverType, int coverCost, String coverOrigin) {
		super();
		this.coverType = coverType;
		this.coverCost = coverCost;
		this.coverOrigin = coverOrigin;
	}
}
