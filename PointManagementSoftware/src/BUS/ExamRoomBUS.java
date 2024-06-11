package BUS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ClassesDAO;
import DAO.ExamRoomDAO;
import DTO.ClassesDTO;
import DTO.ExamRoomDTO;

public class ExamRoomBUS {
//	private ClassesDAO classesDAO;
//
//    public ClassesBUS() {
//        classesDAO = new ClassesDAO();
//    }
//
//    public ArrayList<ClassesDTO> getAllClasses() {
//        ArrayList<ClassesDTO> classesList = new ArrayList<>();
//        try {
//            classesList = classesDAO.menuClass();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return classesList;
//    }
	private ExamRoomDAO examRoomDAO;

	public ExamRoomBUS() {
		examRoomDAO = new ExamRoomDAO();
		
	}
	public List<ExamRoomDTO> getAll()
	{
		List<ExamRoomDTO> lst = new ArrayList<ExamRoomDTO>();
		try {
			lst = examRoomDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
}
