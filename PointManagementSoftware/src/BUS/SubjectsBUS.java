package BUS;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.SubjectsDAO;
import DTO.SubjectsDTO;

public class SubjectsBUS {
	private SubjectsDAO subjectsDAO;
	
	public SubjectsBUS() {
		this.subjectsDAO = new SubjectsDAO();
	}

	public List<SubjectsDTO> showListSubjectsBySemesterAndYear(String semester, int year) throws SQLException{
		return subjectsDAO.getSubjectsBySemesterAndYear(semester, year);
	}
	
	public List<SubjectsDTO> getAllSubject() throws SQLException {
		return subjectsDAO.getAll();
	}
	
	public SubjectsDTO getById(String subId) throws SQLException {
		return subjectsDAO.getSubjectById(subId);
	}
}
