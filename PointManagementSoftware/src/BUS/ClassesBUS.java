package BUS;

import java.util.ArrayList;

import DAO.ClassesDAO;
import DTO.ClassesDTO;

import javax.swing.*;

public class ClassesBUS {
        private ClassesDAO classesDAO;
        public ClassesBUS() {
                classesDAO = new ClassesDAO();
        }
        public ArrayList<ClassesDTO> getAllClasses() {
                ArrayList<ClassesDTO> classesList = new ArrayList<>();
                try {
                        classesList = classesDAO.menuClass();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return classesList;
        }

        public boolean addClass(ClassesDTO cls) {
                try {
                        if (classesDAO.isClassIdExist(cls.getClass_id())) {
                                return false;
                        } else {
                                return classesDAO.addClass(cls);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }

        public boolean updateClass(ClassesDTO cls) {
                try {
                        return classesDAO.updateClass(cls);
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }
        public boolean deleteClass(String class_id) {
                try {
                        return classesDAO.deleteClass(class_id);
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }


}
