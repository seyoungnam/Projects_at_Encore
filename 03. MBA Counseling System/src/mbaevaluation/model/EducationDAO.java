
package mbaevaluation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mbaevaluation.model.dto.EducationDTO;
import mbaevaluation.model.util.DBUtil;

public class EducationDAO {
	private static EducationDAO instance = new EducationDAO();
	
	EducationDAO(){};
	public static EducationDAO getInstance() {
		return instance;
	}
	
	// 데이터 넣기
	public static boolean insertEducation(ArrayList<EducationDTO> education) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			for(int i=0; i<education.size(); i++) {
				EducationDTO item = education.get(i);
				pstmt = con.prepareStatement("insert into education values(?, ?, ?, ?, ?)");
				pstmt.setString(1, item.getSchool_id());
				pstmt.setString(2, item.getSchool_name());
				pstmt.setInt(3, item.getStunum1());
				pstmt.setInt(4, item.getNum_faculty());
				pstmt.setFloat(5, item.getIt_concentration());
				
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

	
	// school_id로 해당학교 education 관련 데이터 반환
	public static EducationDTO getEducation(String schoolId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		EducationDTO education = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from education where school_id=?");
			pstmt.setString(1, schoolId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				education = new EducationDTO(rset.getString(1), rset.getString(2), rset.getInt(3), rset.getInt(4), rset.getFloat(5));
			}			
		} finally {
		   DBUtil.close(con, pstmt, rset);
		}
		return education;
	}
	
	// 모든 학교 education 관련 데이터 반환
	public static ArrayList<EducationDTO> getAllEducation() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<EducationDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from education");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<EducationDTO>();
			while(rset.next()) {
				list.add(new EducationDTO(rset.getString(1), rset.getString(2), rset.getInt(3), rset.getInt(4), rset.getFloat(5)));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
	    }
		return list;
	}
}
