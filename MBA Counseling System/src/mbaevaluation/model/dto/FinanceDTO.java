/**
CREATE TABLE finance (
       school_id         	VARCHAR2(20)  PRIMARY KEY,  
       tuition             	NUMBER(20) NOT NULL,  
       room_board         	NUMBER(20) NOT NULL,    
       salary               NUMBER(20) NOT NULL  
); */

package mbaevaluation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FinanceDTO {
	private String school_id;
	private int tuition;
	private int room_board;
	private int salary;
 

//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append(" 1. MBA 학교 id : ");
//		builder.append(school_id);
//		builder.append(" 2. 등록금 : ");
//		builder.append(tuition);
//		builder.append(" 3. 기숙사비 : ");
//		builder.append(room_board);
//		builder.append(" 4. 연봉 : ");
//		builder.append(salary);
//		return builder.toString();
//	}
}
