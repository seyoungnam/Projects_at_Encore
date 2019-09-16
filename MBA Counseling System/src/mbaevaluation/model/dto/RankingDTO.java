/**
CREATE TABLE ranking (
       school_id         	VARCHAR2(20)  PRIMARY KEY,   
       yr20                 NUMBER(20)  NOT NULL
); */

 
package mbaevaluation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RankingDTO {
	private String school_id;
	private int yr20;
	
//	
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append(" 1. MBA ÇÐ±³ id : ");
//		builder.append(school_id);
//		builder.append(" 2. 2020³â ·©Å· : ");
//		builder.append(yr20);
//		return builder.toString();
//	}
}
