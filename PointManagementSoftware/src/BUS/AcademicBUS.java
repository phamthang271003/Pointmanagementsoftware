package BUS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.AcademicDAO;
import DTO.AcademicDTO;

public class AcademicBUS {
	 private static final Logger LOGGER = Logger.getLogger(AcademicBUS.class.getName());
	    private AcademicDAO academicDAO;

	    public AcademicBUS() {
	        this(new AcademicDAO());
	    }

	    public AcademicBUS(AcademicDAO academicDAO) {
	        this.academicDAO = academicDAO;
	    }

	    public ArrayList<AcademicDTO> getAllAcademic() {
	        ArrayList<AcademicDTO> acedemicList = new ArrayList<>();
	        try {
	        	acedemicList = academicDAO.listAcademic();
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Lỗi khi tìm danh sách phòng ban", e);
	        }
	        return acedemicList;
	    }
	    
	    public int getNextAcademicID() {
	        int nextID = 0;
	        try {
	            nextID = academicDAO.getNextID_Academic();
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Lỗi khi lấy ID tiếp theo cho phòng ban", e);
	        }
	        return nextID;
	    }
	    
	    public void addAcademic(AcademicDTO data) {
	        try {
	        	academicDAO.insertAcademic(data);
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Lỗi khi thêm khoa", e);
	        }
	    }

	    public void removeAcademic(String academic_id) {
	        try {
	        	academicDAO.deleteAcademic(academic_id);
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Lỗi xóa khoa", e);
	        }
	    }

	    public void updateAcademic(AcademicDTO data) {
	        try {
	        	academicDAO.updateAcademic(data);
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Lỗi cập nhật khoa", e);
	        }
	    }
	   
}
