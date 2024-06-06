package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ClassesDAO;
import DTO.ClassesDTO;

public class ClassesBUS {
    private ClassesDAO classesDAO;

    public ClassesBUS() {
        classesDAO = new ClassesDAO();
    }

    public ArrayList<ClassesDTO> getAllClasses() {
        ArrayList<ClassesDTO> classesList = new ArrayList<>();
        try {
            classesList = classesDAO.menuClass();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classesList;
    }
}
