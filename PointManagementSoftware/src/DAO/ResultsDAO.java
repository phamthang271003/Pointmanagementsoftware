package DAO;

import DTO.*;
import java.util.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ResultsDAO {
	private Connection conn;

	public ResultsDAO() {
		this.conn = DataProvider.getInstance().getConnection();
	}
	
	public List<ResultsDTO> getListStudentBySubject(String subId) {
		List<ResultsDTO> lstResult = new ArrayList<ResultsDTO>();
		try {
			String query = "{call LayDanhSachKetQuaTheoMon(?)}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			callStmt.setString(1, subId);
			
			ResultSet rs = callStmt.executeQuery();
			while(rs.next()) {
				ResultsDTO result = new ResultsDTO();
				result.setSub_id(rs.getString(1));
				result.setStu_id(rs.getString(2));
				result.setRes_time(rs.getInt(3));
				result.setRes_score(rs.getFloat(4));
				result.setRes_score_2(rs.getFloat(5));
				result.setAvg_score(rs.getFloat(6));
				lstResult.add(result);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lstResult;
	}
	
	public int updateScore(String stuId, String subId, float diem1, float diem2, float diemTB) {
		int check = -1;
		
		try {
			String query = "{call CapNhatDiemSinhVien(?, ?, ?, ?, ?)}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			callStmt.setString(1, stuId);
			callStmt.setString(2, subId);
			callStmt.setFloat(3, diem1);
			callStmt.setFloat(4, diem2);
			callStmt.setFloat(5, diemTB);
			
			check = callStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return check;
	}
}
