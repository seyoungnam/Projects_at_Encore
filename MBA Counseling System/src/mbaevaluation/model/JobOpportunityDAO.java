/**
CREATE TABLE jobopportunity (
       state_id         	    VARCHAR2(20)  PRIMARY KEY,
       state_name               VARCHAR2(20)  NOT NULL,
       gdp_growth        	    VARCHAR2(20)  NOT NULL,
   
); */

package mbaevaluation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mbaevaluation.model.dto.JobOpportunityDTO;
import mbaevaluation.model.util.DBUtil;

public class JobOpportunityDAO {

	private static JobOpportunityDAO instance = new JobOpportunityDAO();
	
	JobOpportunityDAO(){};
	public static JobOpportunityDAO getInstance() {
		return instance;
	}
	
	
	// 데이터 넣기
		public static boolean insertJobOpportunity(ArrayList<JobOpportunityDTO> jobopportunity) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = DBUtil.getConnection();
				
				for(int i=0; i<jobopportunity.size(); i++) {
					JobOpportunityDTO item = jobopportunity.get(i);
					pstmt = con.prepareStatement("insert into job oppotunity values(?, ?, ?)");
					pstmt.setString(1, item.getState_id());
					pstmt.setString(2, item.getState_name());
					pstmt.setInt(3, item.getGdp_growth());
  					
					int result = pstmt.executeUpdate();
					
					if(result != 1){
						return true;
					}
				}
			
			}finally {
				DBUtil.close(con, pstmt);
			}
			return false;
		}

		
	// state_id로 해당 주의 경제관련 데이터 반환
	public static JobOpportunityDTO getJobOpportunity(String stateId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		JobOpportunityDTO jobopportunity = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from jobopportunnity where state_id=?");
			pstmt.setString(1, stateId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				jobopportunity = new JobOpportunityDTO(rset.getString(1), rset.getString(2), rset.getInt(3));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return jobopportunity;
	}

 
	// 모든 주  경제관련 데이터 반환
	public static ArrayList<JobOpportunityDTO> getAllJobOpportunity() throws SQLException {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    ArrayList<JobOpportunityDTO> list = null;
	    try {
	    	con = DBUtil.getConnection();
	    	pstmt = con.prepareStatement("select * from jobopportunity");
	    	rset = pstmt.executeQuery();
	    	
	    	list = new ArrayList<JobOpportunityDTO>();
	    	while(rset.next()){
	    		list.add(new JobOpportunityDTO(rset.getString(1), rset.getString(2), rset.getInt(3)));
	    	}
	    }finally {
	    	DBUtil.close(con, pstmt, rset);
	    }
	    return list;
	    
	}
}