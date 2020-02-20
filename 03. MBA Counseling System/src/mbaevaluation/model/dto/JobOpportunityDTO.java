/**
CREATE TABLE jobopportunity (
       state_id         	    VARCHAR2(20)  PRIMARY KEY, 
       state_name               VARCHAR2(20)  NOT NULL,  
       gdp_growth        	    Number(20)  NOT NULL    
); */

package mbaevaluation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class JobOpportunityDTO {
	private String state_id;
	private String state_name;
	private int gdp_growth;
	
//	
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append(" 1.  주 번호 : ");
//		builder.append(state_id);
//		builder.append(" 2.  주 이름 : ");
//		builder.append(state_name);
//		builder.append(" 3. GDP 성장률 : ");
//		builder.append(gdp_growth);
//		return builder.toString();
//	}
}
