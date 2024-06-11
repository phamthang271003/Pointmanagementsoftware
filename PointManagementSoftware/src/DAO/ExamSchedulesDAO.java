package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DTO.ExamSchedulesDTO;
import DTO.SubjectsDTO;

public class ExamSchedulesDAO {
	private Connection conn;
	
	public ExamSchedulesDAO() {
		this.conn = DataProvider.getInstance().getConnection();
	}
	
	public List<ExamSchedulesDTO> getExamSchedulesBySemesterAndYear(String semester, String year) {
		List<ExamSchedulesDTO> lstSchedule = new ArrayList<ExamSchedulesDTO>();
		try {
			String query = "{call LayLichThiTheoHocKyNam(?,?)}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			callStmt.setString(1, semester);
			callStmt.setString(2, year);
			
			ResultSet rs = callStmt.executeQuery();
			while (rs.next()) {
				ExamSchedulesDTO schedule = new ExamSchedulesDTO();
				schedule.setExam_id(rs.getString(1));
				schedule.setSemester(rs.getString(2));
				schedule.setRoom_id(rs.getString(3));
				schedule.setExam_start(rs.getString(4));
				schedule.setExam_time(rs.getString(5));
				schedule.setExam_date(rs.getDate(6));
				schedule.setExam_year(rs.getString(7));
				lstSchedule.add(schedule);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lstSchedule;
	}
	
	public ExamSchedulesDTO getExamScheduleById(String examId) {
		ExamSchedulesDTO schedule = null;
		try {
			String query = "{call LayLichThiTheoMaLich(?)}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			callStmt.setString(1, examId);
			
			ResultSet rs = callStmt.executeQuery();
			if (rs.next()) {
				schedule = new ExamSchedulesDTO();
				schedule.setExam_id(rs.getString(1));
				schedule.setSemester(rs.getString(2));
				schedule.setRoom_id(rs.getString(3));
				schedule.setExam_start(rs.getString(4));
				schedule.setExam_time(rs.getString(5));
				schedule.setExam_date(rs.getDate(6));
				schedule.setExam_year(rs.getString(7));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return schedule;
	}
	
	public List<ExamSchedulesDTO> getAll(){
		List<ExamSchedulesDTO> lstSchedule = new ArrayList<ExamSchedulesDTO>();
		try {
			String query = "{call LayTatCaLichThi}";
			CallableStatement callStmt = conn.prepareCall(query);
			
			ResultSet rs = callStmt.executeQuery();
			while (rs.next()) {
				ExamSchedulesDTO schedule = new ExamSchedulesDTO();
				schedule.setExam_id(rs.getString(1));
				schedule.setSemester(rs.getString(2));
				schedule.setRoom_id(rs.getString(3));
				schedule.setExam_start(rs.getString(4));
				schedule.setExam_time(rs.getString(5));
				schedule.setExam_date(rs.getDate(6));
				schedule.setExam_year(rs.getString(7));
				lstSchedule.add(schedule);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lstSchedule;
	}
	public int create(ExamSchedulesDTO e) {
	    int check = 0;
	    try {
	     
	        String sql = "{call DangKyLichThi(?, ?, ?, ?, ?, ?, ?)}";
	        CallableStatement callStmt = conn.prepareCall(sql);
	        
	        // Set the parameters for the stored procedure
	        callStmt.setString(1, e.getExam_id());
	        callStmt.setString(2, e.getSemester());
	        callStmt.setString(3, e.getRoom_id());
	        callStmt.setString(4, e.getExam_start());
	        callStmt.setString(5, e.getExam_time());
	        callStmt.setDate(6, e.getExam_date());
	        callStmt.setString(7, e.getExam_year());
	        
	        // Execute the stored procedure
	        check = callStmt.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return check;
	}
}
