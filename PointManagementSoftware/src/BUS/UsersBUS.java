package BUS;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import DAO.UsersDAO;
import DTO.UsersDTO;

public class UsersBUS {
	private UsersDAO userDAO;
	public UsersBUS() {
		userDAO = new UsersDAO();
	}

	public UsersDTO getTeacherById(String id) {
		return userDAO.getTeacherById(id);
	}
}
