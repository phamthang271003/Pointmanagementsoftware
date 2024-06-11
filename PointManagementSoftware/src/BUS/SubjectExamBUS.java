package BUS;

import java.util.List;

import DAO.SubjectExamDAO;
import DTO.ExamSchedulesDTO;
import DTO.SubjectExamDTO;

public class SubjectExamBUS {
	private SubjectExamDAO subjectExamDAO;

	public SubjectExamBUS() {
		this.subjectExamDAO = new SubjectExamDAO();
	}
	public int create(SubjectExamDTO e)
	{
		return subjectExamDAO.create(e);
	}
	public List<SubjectExamDTO> getAll()
	{
		return subjectExamDAO.getAll();
	}
}
