package DAO;

import DTO.ExamRoomDTO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExamRoomDAO {
        private ArrayList<ExamRoomDTO> examRooms;

        public ExamRoomDAO() {
                examRooms = new ArrayList<>();
        }

        public ArrayList<ExamRoomDTO> menuExamRoom() {
                ArrayList<ExamRoomDTO> list = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAllExamRoom}");
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                ExamRoomDTO examRoom = new ExamRoomDTO();
                                examRoom.setRoomID(rs.getString("room_id"));
                                examRoom.setRoomName(rs.getString("room_name"));
                                examRoom.setQuantity(rs.getInt("room_quantity"));
                                list.add(examRoom);
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return list;
        }
        public boolean isExamRoomIdExist(String roomID) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getExamRoomById(?)}");
                        stmt.setString(1, roomID);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                                return true;
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return false;
        }
        public boolean addExamRoom(ExamRoomDTO examRoom) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_insertExamRoom(?,?,?)}");
                        stmt.setString(1, examRoom.getRoomID());
                        stmt.setString(2, examRoom.getRoomName());
                        stmt.setInt(3, examRoom.getQuantity());
                        stmt.execute();
                        helper.close();
                        return true;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }
        public boolean updateExamRoom(ExamRoomDTO examRoom) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_updateExamRoom(?,?,?)}");
                        stmt.setString(1, examRoom.getRoomID());
                        stmt.setString(2, examRoom.getRoomName());
                        stmt.setInt(3, examRoom.getQuantity());
                        stmt.execute();
                        helper.close();
                        return true;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }
        public boolean deleteExamRoom(String roomID) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_deleteExamRoom(?)}");
                        stmt.setString(1, roomID);
                        stmt.execute();
                        helper.close();
                        return true;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }
}
