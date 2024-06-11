package BUS;
import DAO.ExamRoomDAO;
import DTO.AcademicDTO;
import DTO.ExamRoomDTO;

import java.util.ArrayList;

public class ExamRoomBUS {

        private ExamRoomDAO examRoomDAO;
        public ExamRoomBUS() {
                examRoomDAO = new ExamRoomDAO();
        }
        public ArrayList<ExamRoomDTO> getAllExamRoom() {

                ArrayList<ExamRoomDTO> examRoomList = new ArrayList<>();
                try{
                        examRoomList = examRoomDAO.menuExamRoom();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return examRoomList;
        }
        public boolean addExamRoom(ExamRoomDTO examRoom) {
                try {
                        if (examRoomDAO.isExamRoomIdExist(examRoom.getRoomID())) {
                                return false;
                        }
                        else {
                                return examRoomDAO.addExamRoom(examRoom);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }

        public boolean updateExamRoom(ExamRoomDTO examRoom) {
                try {
                        return examRoomDAO.updateExamRoom(examRoom);
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }
        public boolean deleteExamRoom(String roomID) {
                try {
                        return examRoomDAO.deleteExamRoom(roomID);
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }
}
