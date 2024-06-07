package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DTO.StudentDTO;

public class StudentDAO {
	private Connection conn;
	
	public StudentDAO() {
		conn = DataProvider.getInstance().getConnection();
	}
	
	public List<StudentDTO> getAll() throws SQLException {
		List<StudentDTO> students = new ArrayList<StudentDTO>();
		
		String sql = "select * from students";
		
		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			StudentDTO student = new StudentDTO();
			student.setStu_id(rs.getString("stu_id"));
			student.setStu_name(rs.getString("stu_name"));
			student.setStu_day_entry(Date.valueOf(rs.getString("stu_day_entry")));
			student.setStu_semester(rs.getInt("stu_semester"));
			student.setStu_sex(rs.getString("stu_sex"));
			student.setStu_dob(Date.valueOf(rs.getString("stu_dob")));
			student.setStu_address(rs.getString("stu_address"));
			student.setAca_id(rs.getString("aca_id"));
			student.setClass_id(rs.getString("class_id"));
			
			students.add(student);
		}
		
		return students;
	}
}
