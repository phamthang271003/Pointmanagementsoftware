package BUS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.SubjectDAO;
import DTO.AcademicDTO;
import DTO.SubjectDTO;

public class SubjectBUS {
	private static final Logger LOGGER = Logger.getLogger(AcademicBUS.class.getName());
	private SubjectDAO subjectDAO;

	public SubjectBUS() {
		this(new SubjectDAO());
	}

	public SubjectBUS(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

	public ArrayList<SubjectDTO> getAllSubject() {
		ArrayList<SubjectDTO> subjectList = new ArrayList<>();
		try {
			subjectList = subjectDAO.listSubject();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Lỗi khi tìm danh sách môn học", e);
		}
		return subjectList;
	}
	 public int getNextSubjectID() {
	        int nextID = 0;
	        try {
	            nextID = subjectDAO.getNextID_Subject();
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Lỗi khi lấy ID tiếp theo cho môn học", e);
	        }
	        return nextID;
	    }
	    
	    public void addSubject(SubjectDTO data) {
	        try {
	        	subjectDAO.insertSubject(data);
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Lỗi khi thêm môn học", e);
	        }
	    }
	    public void removeSubject(String subject_id) {
	        try {
	        	subjectDAO.deleteSubject(subject_id);
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Lỗi xóa môn học", e);
	        }
	    }

	    
	    public void updateSubject(SubjectDTO data) {
	        try {
	        	subjectDAO.updateSubjects(data);
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Lỗi cập nhật môn học", e);
	        }
	    }
	    public ArrayList<SubjectDTO> getSubjectsByAcademic(String academicID) {

	                    ArrayList<SubjectDTO> subjectList = new ArrayList<>();
	                    try{
	                            subjectList = subjectDAO.getSubjectsByAcademic(academicID);
	                    } catch (Exception e) {
	                            e.printStackTrace();
	                    }
	                    return subjectList;
	            }
	    
	    
}
