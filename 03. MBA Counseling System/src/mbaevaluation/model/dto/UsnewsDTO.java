/**
CREATE TABLE usnews (
       school_name         	VARCHAR2(100)  PRIMARY KEY,   
       rank                 NUMBER(20)  NOT NULL,
       tuition              NUMBER(20)  NOT NULL,
       student_number       NUMBER(20)  NOT NULL
); */

 
package mbaevaluation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsnewsDTO {
	private String school_name;
	private int rank;
	private int tuition;
	private int student_number;
	
	
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
