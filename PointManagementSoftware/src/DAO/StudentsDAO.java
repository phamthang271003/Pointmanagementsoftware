package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import DTO.StudentsDTO;

public class StudentsDAO {
	private Connection conn;

	public StudentsDAO() {
		this.conn = DataProvider.getInstance().getConnection();
	}
	
	public StudentsDTO getStudentById(String id) {
		StudentsDTO stu = null;
		try {
			CallableStatement stmt = conn.prepareCall("{call laySinhVienTheoMa(?)}");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				stu = new StudentsDTO();
				stu.setStu_id(rs.getString("stu_id"));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_day_entry(rs.getDate("stu_day_entry"));
				stu.setStu_semester(rs.getInt("stu_semester"));
				stu.setStu_sex(rs.getString("stu_sex"));
				stu.setDob(rs.getDate("stu_dob"));
				stu.setAddress(rs.getString("stu_address"));
				stu.setAca_id(rs.getString("aca_name"));
				stu.setClass_id(rs.getString("class_name"));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return stu;
	}
}
