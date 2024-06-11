package BUS;

import DAO.StudentDAO;

public class StudentBUS {

	private StudentDAO studentDAO;
	
	public StudentBUS()
	{
		studentDAO=new StudentDAO();
	}
	
	public String[] takeInforClasses()
	{
		return studentDAO.takeInforClasses();
	}
	
	public String[] takeInforAca()
	{
		return studentDAO.takeInforAca();
	}
	
	public int checkStudentId(String idStudent)
	{
		return studentDAO.checkStudentId(idStudent);
	}
	
	public void insertStudent(String stu_id, String stu_name, String stu_day_entry, String stu_semester, String stu_sex, String stu_dob, String stu_address, String aca_id, String class_id) {
		studentDAO.insertStudent(stu_id, stu_name, stu_day_entry, stu_semester, stu_sex, stu_dob, stu_address, aca_id, class_id);
	}
}
