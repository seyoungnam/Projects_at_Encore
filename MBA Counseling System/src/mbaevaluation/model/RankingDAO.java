/**
CREATE TABLE ranking (
       school_id         	VARCHAR2(20)  PRIMARY KEY,   
       yr20                 VARCHAR2(20)  NOT NULL
); */


package mbaevaluation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
import mbaevaluation.model.dto.RankingDTO;
import mbaevaluation.model.util.DBUtil;

public class RankingDAO {
	private static RankingDAO instance = new RankingDAO();
	
	RankingDAO(){};
	public static RankingDAO getInstance() {
		return instance;
	}
	
	// 데이터 넣기
		public boolean insertRanking(ArrayList<RankingDTO> ranking) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = DBUtil.getConnection();
				
				for(int i=0; i< ranking.size(); i++) {
					RankingDTO item = ranking.get(i);
					pstmt = con.prepareStatement("insert into ranking values(?, ?)");
					pstmt.setString(1, item.getSchool_id());
					pstmt.setInt(2, item.getYr20());

					
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

		
    // school_id로 해당 학교 ranking 관련 데이터 반환
	public static RankingDTO getRanking(String schoolId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		RankingDTO ranking = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from rankin where school_id?");
			pstmt.setString(1, schoolId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				ranking = new RankingDTO(rset.getString(1), rset.getInt(2));
			}			
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return ranking;
	}
	
    // 모든 학교 ranking 관련 데이터 반환
	public static ArrayList<RankingDTO> getAllRanking() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RankingDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("selet * from ranking");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<RankingDTO>();
			while(rset.next()) {
				list.add(new RankingDTO(rset.getString(1), rset.getInt(2)));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}		
}
 
