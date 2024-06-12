package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.ExamSchedulesDTO;
import DTO.SubjectExamDTO;
import DTO.SubjectsDTO;

public class SubjectExamDAO {
	private Connection conn;
	public SubjectExamDAO() {
		this.conn = DataProvider.getInstance().getConnection();

}

	public int create(SubjectExamDTO se) {
	    int check = 0;
	    try {
	        String sql = "{call InsertSubExam(?, ?, ?)}";
	        CallableStatement callStmt = conn.prepareCall(sql);

	        // Set the parameters for the stored procedure
	        callStmt.setString(1, se.getExam_id());
	        callStmt.setString(2, se.getSub_id());
	        callStmt.setInt(3, se.getExam_time());

	        // Execute the stored procedure
	        check = callStmt.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return check;
	}
	public List<SubjectExamDTO> getAll()
	{
		List<SubjectExamDTO> lst= new ArrayList<SubjectExamDTO>();
		try {
			String query = "{call getSubjectExam }";
			CallableStatement callStmt = conn.prepareCall(query);
			
			ResultSet rs = callStmt.executeQuery();
			while (rs.next()) {
				SubjectExamDTO s = new SubjectExamDTO();
				s.setExam_id(rs.getString(1));
				s.setSub_id(rs.getString(2));
				s.setExam_time(rs.getInt(3));
				lst.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return  lst;
	}
	
		
}

