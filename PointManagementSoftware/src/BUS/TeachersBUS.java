package BUS;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.SubjectsDAO;
import DAO.TeachersDAO;
import DTO.TeachersDTO;

public class TeachersBUS {
	private TeachersDAO teachersDAO;
	
	public TeachersBUS() {
		this.teachersDAO = new TeachersDAO();
	}
	
	public List<TeachersDTO> getAll() {
		return teachersDAO.getAll();
	}
	
	public TeachersDTO getTeacherById(String teaId) {
		return teachersDAO.getTeacherById(teaId);
	}
}
