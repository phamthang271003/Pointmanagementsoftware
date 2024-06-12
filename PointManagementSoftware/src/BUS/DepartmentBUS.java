package BUS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.DepartmentDAO;
import DTO.DepartmentsDTO;

public class DepartmentBUS {
    private static final Logger LOGGER = Logger.getLogger(DepartmentBUS.class.getName());
    private DepartmentDAO departmentDAO;

    public DepartmentBUS() {
        this(new DepartmentDAO());
    }

    public DepartmentBUS(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public ArrayList<DepartmentsDTO> getAllDepartments() {
        ArrayList<DepartmentsDTO> departmentList = new ArrayList<>();
        try {
            departmentList = departmentDAO.listDepartment();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm danh sách phòng ban", e);
        }
        return departmentList;
    }
    
    public int getNextDepartmentID() {
        int nextID = 0;
        try {
            nextID = departmentDAO.getNextID_Department();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy ID tiếp theo cho phòng ban", e);
        }
        return nextID;
    }
    
    public void addDepartment(DepartmentsDTO data) {
        try {
            departmentDAO.insertDepartment(data);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi thêm khoa", e);
        }
    }

    public void removeDepartment(String depart_id) {
        try {
            departmentDAO.deleteDepartment(depart_id);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi xóa khoa", e);
        }
    }

    public void updateDepartment(DepartmentsDTO data) {
        try {
            departmentDAO.updateDepartment(data);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi cập nhật khoa", e);
        }
    }
}
