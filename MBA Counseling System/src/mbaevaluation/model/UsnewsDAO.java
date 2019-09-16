/**
CREATE TABLE usnews (
       school_name         	VARCHAR2(20)  PRIMARY KEY,   
       rank                 NUMBER(20)  NOT NULL,
       tuition              NUMBER(20)  NOT NULL,
       student_number       NUMBER(20)  NOT NULL,
); */


package mbaevaluation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import mbaevaluation.model.dto.UsnewsDTO;
import mbaevaluation.model.util.DBUtil;

public class UsnewsDAO {
	private static UsnewsDAO instance = new UsnewsDAO();
	
	UsnewsDAO(){};
	public static UsnewsDAO getInstance() {
		return instance;
	}
	
	// 데이터 넣기
		public static boolean insertUsnews(ArrayList<UsnewsDTO> usnews) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = DBUtil.getConnection();
				
				for(int i=0; i< usnews.size(); i++) {
					UsnewsDTO item = usnews.get(i);
					pstmt = con.prepareStatement("insert into usnews values(?, ?, ?, ?)");
					pstmt.setString(1, item.getSchool_name());
					pstmt.setInt(2, item.getRank());
					pstmt.setInt(3, item.getTuition());
					pstmt.setInt(4, item.getStudent_number());
			
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
	}		

 
