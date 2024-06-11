package BUS;

import java.util.List;

import DAO.ResultsDAO;
import DTO.ResultsDTO;

public class ResultsBUS {
	//getListStudentBySubject
	private ResultsDAO resultDAO;
	
	public ResultsBUS() {
		resultDAO = new ResultsDAO();
	}
	
	public List<ResultsDTO> getListStudentBySubject(String subId) {
		return resultDAO.getListStudentBySubject(subId);
	}
	
	public int updateScore(String stuId, String subId, float diem1, float diem2, float diemTB) {
		return resultDAO.updateScore(stuId, subId, diem1, diem2, diemTB);
	}
}
