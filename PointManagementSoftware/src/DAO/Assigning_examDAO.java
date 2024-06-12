package DAO;

import DTO.Assigning_examDTO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Assigning_examDAO {
        private ArrayList<Assigning_examDTO> assigning_exam;
        public Assigning_examDAO() {
                assigning_exam = new ArrayList<>();
        }
        public ArrayList<Assigning_examDTO> menuAssigning_exam() {
                ArrayList<Assigning_examDTO> list = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAllAssigning_exam}");
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                Assigning_examDTO stu = new Assigning_examDTO();
                                stu.setSub_id(rs.getString("sub_name"));
                                stu.setTea_id(rs.getString("tea_name"));
                                list.add(stu);
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return list;
        }
        public boolean isAssigning_examIdExist(String sub_id, String tea_id) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAssigning_examById(?, ?)}");
                        stmt.setString(1, sub_id);
                        stmt.setString(2, tea_id);
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
        public boolean addAssigning_exam(Assigning_examDTO stu) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_insertAssigning_exam(?, ?)}");
                        stmt.setString(1, stu.getSub_id());
                        stmt.setString(2, stu.getTea_id());
                        int result = stmt.executeUpdate();
                        helper.close();
                        return result > 0;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }

}
