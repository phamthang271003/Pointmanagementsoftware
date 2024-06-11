package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.ExamSchedulesDTO;
import DTO.TeachersDTO;

public class TeachersDAO {
	private Connection conn;
	
	public TeachersDAO() {
		this.conn = DataProvider.getInstance().getConnection();
	}
	
	public List<TeachersDTO> getAll() {
		List<TeachersDTO> lstTeachers = new ArrayList<TeachersDTO>();
		try {
			String query = "{call LayTatCaGiangVien}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			ResultSet rs = callStmt.executeQuery();
			while (rs.next()) {
				TeachersDTO teacher = new TeachersDTO();
				
				teacher.setTea_id(rs.getString(1));
				teacher.setTea_name(rs.getString(2));
				teacher.setTea_sex(rs.getString(3));
				teacher.setDepart_id(rs.getString(4));
				lstTeachers.add(teacher);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lstTeachers;
	}
	
	public TeachersDTO getTeacherById(String teaId) {
		TeachersDTO teacher = null;
		try {
			String query = "{call LayGiangVienTheoMa(?)}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			callStmt.setString(1, teaId);
			
			ResultSet rs = callStmt.executeQuery();
			if (rs.next()) {
				teacher = new TeachersDTO();
				teacher.setTea_id(rs.getString(1));
				teacher.setTea_name(rs.getString(2));
				teacher.setTea_sex(rs.getString(3));
				teacher.setDepart_id(rs.getString(4));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return teacher;
	}
}
