package BUS;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.RegisExamDAO;
import DTO.RegisExamDTO;

public class RegisExamBUS {
	private RegisExamDAO regisBUS;
	public RegisExamBUS() {
		regisBUS = new RegisExamDAO();
	}
	
	public int RegisterExamSchedule(String examId, String teaId) {
		return regisBUS.RegisterExamSchedule(examId, teaId);
	}
	
	public List<RegisExamDTO> getAll() {
		return regisBUS.getAll();
	}
}
