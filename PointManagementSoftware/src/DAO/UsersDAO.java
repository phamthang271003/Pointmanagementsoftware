package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import DTO.TeachersDTO;
import DTO.UsersDTO;

public class UsersDAO {
	private Connection conn;
	
	public UsersDAO() {
		this.conn = DataProvider.getInstance().getConnection();
	}
	
	public UsersDTO getTeacherById(String id) {
		UsersDTO user = null;
		try {
			String query = "{call LayTaiKhoanTheoId(?)}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			callStmt.setString(1, id);
			
			ResultSet rs = callStmt.executeQuery();
			if (rs.next()) {
				user = new UsersDTO();
				user.setU_id(rs.getString(1));
				user.setU_name(rs.getString(2));
				user.setU_password(rs.getString(3));
				user.setU_role(rs.getString(4));
				user.setTea_id(rs.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}
}
