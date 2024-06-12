package BUS;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.ExamSchedulesDAO;
import DTO.ExamSchedulesDTO;

public class ExamSchedulesBUS {
	private ExamSchedulesDAO examSchedulesDAO;
	
	public ExamSchedulesBUS () {
		examSchedulesDAO = new ExamSchedulesDAO();
	}
	
	public List<ExamSchedulesDTO> getExamSchedulesBySemesterAndYear(String semester, String year) {
		return examSchedulesDAO.getExamSchedulesBySemesterAndYear(semester, year);
	}
	
	public ExamSchedulesDTO getExamScheduleById(String examId) {
		return examSchedulesDAO.getExamScheduleById(examId);
	}
	
	public List<ExamSchedulesDTO> getAll(){
		return examSchedulesDAO.getAll();
	}
	public int create(ExamSchedulesDTO e)
	{
		return examSchedulesDAO.create(e);
	}
}
