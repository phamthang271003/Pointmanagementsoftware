package BUS;

import DAO.StudentDAO;
import DTO.StudentsDTO;
//import DTO.StudyRecordDTO;

import java.util.ArrayList;

public class StudentsBUS {

        private StudentDAO studentsDAO;
        public StudentsBUS() {
                studentsDAO = new StudentDAO();
        }
        public ArrayList<StudentsDTO> getAllStudent() {

                ArrayList<StudentsDTO> studentList = new ArrayList<>();
                try{
                        studentList = studentsDAO.menuStudent();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return studentList;
        }
        public boolean addStudent(StudentsDTO stu) {
                try {
                        if (studentsDAO.isStudentIdExist(stu.getStu_id())) {
                                return false;
                        }
                        else {
                                return studentsDAO.addStudent(stu);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }
        public  boolean updateStudent(StudentsDTO stu) {
                try {
                        return studentsDAO.updateStudent(stu);
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }
        public boolean deleteStudent(String stuId) {
                try {
                        return studentsDAO.deleteStudent(stuId);
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }
        public StudentsDTO searchStudent(String stuId) {
                try {
                        return studentsDAO.searchStudent(stuId);
                } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                }
        }
//        public ArrayList<StudyRecordDTO> getStudyRecords(String stuId) {
//                try {
//                        return studentsDAO.getStudyRecords(stuId);
//                } catch (Exception e) {
//                        e.printStackTrace();
//                        return null;
//                }
//        }
}
