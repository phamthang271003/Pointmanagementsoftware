package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.*;

public class RegisExamDAO {
	private Connection conn;
	
	public RegisExamDAO() {
		this.conn = DataProvider.getInstance().getConnection();
	}
	
	public int RegisterExamSchedule(String examId, String teaId) {
		int check = -1;
		
		try {
			String query = "{call DangKyCoiThi(?, ?)}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			callStmt.setString(1, teaId);
			callStmt.setString(2, examId);
			
			check = callStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return check;
	}
	
	public List<RegisExamDTO> getAll() {
		List<RegisExamDTO> lstRegis = new ArrayList<RegisExamDTO>();
		
		try {
			String query = "{call LayDanhSachCoiThi}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			ResultSet rs = callStmt.executeQuery();
			while(rs.next()) {
				RegisExamDTO regis = new RegisExamDTO();
				regis.setTea_id(rs.getString(1));
				regis.setExam_id(rs.getString(2));
				lstRegis.add(regis);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return lstRegis;
	}
}
