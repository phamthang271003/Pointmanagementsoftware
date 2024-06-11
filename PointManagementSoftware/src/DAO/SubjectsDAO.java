package DAO;

import DTO.SubjectsDTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectsDAO {
	private Connection conn;

	public SubjectsDAO() {
		this.conn = DataProvider.getInstance().getConnection();
	}
	
	public List<SubjectsDTO> getAll() throws SQLException {
		List<SubjectsDTO> lstSubject = new ArrayList<SubjectsDTO>();
		String query = "{call LayTatCaMonHoc}";
		CallableStatement callStmt = conn.prepareCall(query);
		
		ResultSet rs = callStmt.executeQuery();
		while (rs.next()) {
			SubjectsDTO subject = new SubjectsDTO();
			subject.setSub_id(rs.getString(1));
			subject.setSub_name(rs.getString(2));
			subject.setSub_semester(rs.getString(3));
			subject.setSub_year(rs.getInt(4));
			lstSubject.add(subject);
		}
		return lstSubject;
	}
	
	public List<SubjectsDTO> getSubjectsBySemesterAndYear(String semester, int year) throws SQLException {
		List<SubjectsDTO> lstSubject = new ArrayList<SubjectsDTO>();
		String query = "{call LayMonHocTheoHocKyNamHoc(?, ?)}";
		CallableStatement callStmt = conn.prepareCall(query);
		
		callStmt.setString(1, semester);
		callStmt.setInt(2, year);
		
		ResultSet rs = callStmt.executeQuery();
		while (rs.next()) {
			SubjectsDTO subject = new SubjectsDTO();
			subject.setSub_id(rs.getString(1));
			subject.setSub_name(rs.getString(2));
			subject.setSub_semester(rs.getString(3));
			subject.setSub_year(rs.getInt(4));
			lstSubject.add(subject);
		}
		return lstSubject;
	}
	
	public SubjectsDTO getSubjectById(String subjectId) {
		SubjectsDTO subject = null;
		
		try {
			String query = "{call LayMonHocBangId(?)}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			callStmt.setString(1, subjectId);
			
			ResultSet rs = callStmt.executeQuery();
			if (rs.next()) {
				subject = new SubjectsDTO();
				subject.setSub_id(rs.getString(1));
				subject.setSub_name(rs.getString(2));
				subject.setSub_semester(rs.getString(3));
				subject.setSub_year(rs.getInt(4));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return subject;
	}
}
