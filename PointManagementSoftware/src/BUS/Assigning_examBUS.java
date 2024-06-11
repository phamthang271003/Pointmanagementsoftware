package BUS;

import DAO.Assigning_examDAO;
import DTO.Assigning_examDTO;

import java.util.ArrayList;

public class Assigning_examBUS {
        private Assigning_examDAO assigning_examDAO;

        public Assigning_examBUS() {
                assigning_examDAO = new Assigning_examDAO();
        }

        public ArrayList<Assigning_examDTO> getAllAssigning_exam() {
                ArrayList<Assigning_examDTO> assigning_examList = new ArrayList<>();
                try {
                        assigning_examList = assigning_examDAO.menuAssigning_exam();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return assigning_examList;
        }

        public boolean addAssigning_exam(Assigning_examDTO cls) {
                try {
                        if (assigning_examDAO.isAssigning_examIdExist(cls.getSub_id(), cls.getTea_id())) {
                                return false;
                        } else {
                                return assigning_examDAO.addAssigning_exam(cls);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }
//
//        public boolean updateAssigning_exam(Assigning_examDTO cls) {
//                try {
//                        return assigning_examDAO.updateAssigning_exam(cls);
//                } catch (Exception e) {
//                        e.printStackTrace();
//                        return false;
//                }
//        }
//
//        public boolean deleteAssigning_exam(String assigning_exam_id) {
//                try {
//                        return assigning_examDAO.deleteAssigning_exam(assigning_exam_id);
//                } catch (Exception e) {
//                        e.printStackTrace();
//                        return false;
//                }
//        }
}
