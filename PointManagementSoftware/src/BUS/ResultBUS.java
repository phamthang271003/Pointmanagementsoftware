package BUS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.ResultDAO;
import DTO.AcademicDTO;
import DTO.ResultDTO;

public class ResultBUS {
    private static final Logger LOGGER = Logger.getLogger(ResultBUS.class.getName());
    private ResultDAO resultDAO;

    public ResultBUS() {
        this(new ResultDAO());
    }

    public ResultBUS(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    public ArrayList<ResultDTO> getResultsByClassId(int classId) {
        ArrayList<ResultDTO> results = new ArrayList<>();
        try {
            results = resultDAO.getResultsByClassId(classId);
        } catch (SQLException e) {
            LOGGER.severe("Error fetching results: " + e.getMessage());
        }
        return results;
    }
    
    public void addResult(ResultDTO data) {
        try {
        	resultDAO.insertResult(data);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi thêm môn học cho sinh viên", e);
        }
    }
}
