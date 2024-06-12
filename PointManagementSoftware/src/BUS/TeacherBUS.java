package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.AcademicDAO;
import DAO.TeacherDAO;
import DTO.AcademicDTO;
import DTO.TeacherDTO;

public class TeacherBUS {
	private AcademicDAO academicDAO;

	private TeacherDAO teacherDAO;

	public TeacherBUS() {
		teacherDAO = new TeacherDAO();
	}

	public String[] takeInforDepart() {
		return teacherDAO.takeInforDeparts();
	}

	public List<TeacherDTO> takeAllTeachers() {
		return teacherDAO.takeAllTeachers();
	}

	public List<TeacherDTO> takeAllTeachersByDepartID(String depart_id) {
		return teacherDAO.takeAllTeachersByDepartID(depart_id);
	}

	public void insertTeacher(String teaId, String teaName, String teaSex, String departId) {
		teacherDAO.insertTeacher(teaId, teaName, teaSex, departId);
	}

	public int checkIdTeacher(String id_teacher) {
		return teacherDAO.checkIdTeacher(id_teacher);
	}

	public void updateTeacher(String teaId, String teaName, String teaSex, String departId) {
		teacherDAO.updateTeacher(teaId, teaName, teaSex, departId);
	}

	public void deleteTeacher(String id_teacher) {
		teacherDAO.deleteTeacher(id_teacher);
	}

	public ArrayList<AcademicDTO> getAcademicByDepartment(String departmentID) {
		ArrayList<AcademicDTO> academicList = new ArrayList<>();
		try {
			academicList = academicDAO.getAcademicByDepartment(departmentID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return academicList;
	}
	
	public ArrayList<TeacherDTO> getTeachersByAcademic(String academicID) {
        ArrayList<TeacherDTO> teacherList = new ArrayList<>();
        try{
                teacherList = teacherDAO.getTeachersByAcademic(academicID);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return teacherList;
}
}
