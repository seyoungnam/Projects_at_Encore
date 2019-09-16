/**
CREATE TABLE finance (
       school_id         	VARCHAR2(20)  PRIMARY KEY,
       tuition             	VARCHAR2(20) NOT NULL,
       room_board         	VARCHAR2(20) NOT NULL,
       salary               VARCHAR2(20) NOT NULL
); */

package mbaevaluation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mbaevaluation.model.dto.FinanceDTO;
import mbaevaluation.model.util.DBUtil;

public class FinanceDAO {
	
	private static FinanceDAO instance = new FinanceDAO();
	
	FinanceDAO(){};
	public static FinanceDAO getInstance() {
		return instance;
	}
	
	// 데이터 넣기
		public static boolean insertFinance(ArrayList<FinanceDTO> finance) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = DBUtil.getConnection();
				
				for(int i=0; i< finance.size(); i++) {
					FinanceDTO item = finance.get(i);
					pstmt = con.prepareStatement("insert into finace values(?, ?, ?, ?)");
					pstmt.setString(1, item.getSchool_id());
					pstmt.setInt(2, item.getTuition());
					pstmt.setInt(3, item.getRoom_board());
					pstmt.setInt(4, item.getSalary());
 
					
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
		
	
	// school_id로 해당 학교 finance 관련 데이터 반환
	public static FinanceDTO getFinance(String schoolId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FinanceDTO finance = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from finance where school_id=?");
			pstmt.setString(1, schoolId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				finance = new FinanceDTO(rset.getString(1), rset.getInt(2), rset.getInt(3), rset.getInt(4));
			}			
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return finance;
	}
	
	// 모든 학교 finance 관련 데이터 반환
	public static ArrayList<FinanceDTO> getAllFinance() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FinanceDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from finance");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<FinanceDTO>();
			while(rset.next()){
				list.add(new FinanceDTO(rset.getString(1), rset.getInt(2), rset.getInt(3), rset.getInt(4)));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
