
package mbaevaluation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mbaevaluation.model.dto.IpedsDTO;
import mbaevaluation.model.util.DBUtil;

public class IpedsDAO {
	private static IpedsDAO instance = new IpedsDAO();
	
	IpedsDAO(){};
	public static IpedsDAO getInstance() {
		return instance;
	}
	
	// ������ �ֱ�
	public static boolean insertIpeds(ArrayList<IpedsDTO> ipeds) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			for(int i=0; i<ipeds.size(); i++) {
				IpedsDTO item = ipeds.get(i);
				pstmt = con.prepareStatement("insert into ipeds values(?, ?, ?, ?, ?)");
				pstmt.setString(1, item.getSchool_id());
				pstmt.setString(2, item.getSchool_name());
				pstmt.setString(3, item.getState());
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
}
	
//	// school_id�� �ش��б� education ���� ������ ��ȯ
//	public static EducationDTO getEducation(String schoolId) throws SQLException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		EducationDTO education = null;
//		
//		try {
//			con = DBUtil.getConnection();
//			pstmt = con.prepareStatement("select * from education where school_id=?");
//			pstmt.setString(1, schoolId);
//			rset = pstmt.executeQuery();
//			if(rset.next()) {
//				education = new EducationDTO(rset.getString(1), rset.getString(2), rset.getInt(3), rset.getInt(4), rset.getFloat(5));
//			}			
//		} finally {
//		   DBUtil.close(con, pstmt, rset);
//		}
//		return education;
//	}
//	
//	// ��� �б� education ���� ������ ��ȯ
//	public static ArrayList<EducationDTO> getAllEducation() throws SQLException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		ArrayList<EducationDTO> list = null;
//		try {
//			con = DBUtil.getConnection();
//			pstmt = con.prepareStatement("select * from education");
//			rset = pstmt.executeQuery();
//			
//			list = new ArrayList<EducationDTO>();
//			while(rset.next()) {
//				list.add(new EducationDTO(rset.getString(1), rset.getString(2), rset.getInt(3), rset.getInt(4), rset.getFloat(5)));
//			}
//		}finally {
//			DBUtil.close(con, pstmt, rset);
//	    }
//		return list;
