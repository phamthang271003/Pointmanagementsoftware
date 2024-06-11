package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DepartmentsDTO;

public class DepartmentDAO {
    private Connection con;

    public DepartmentDAO() {
        DataProvider.getInstance().connectToDatabase();
        con = DataProvider.getInstance().getConnection();
    }

    public ArrayList<DepartmentsDTO> listDepartment() throws SQLException {
        ArrayList<DepartmentsDTO> list = new ArrayList<>();
        String sql = "{call sp_GetDepartments}";
        
        try (CallableStatement cstmt = con.prepareCall(sql);
             ResultSet r = cstmt.executeQuery()) {
             
            while (r.next()) {
                String depart_id = r.getString(1);
                String depart_name = r.getString(2);
                DepartmentsDTO data = new DepartmentsDTO(depart_id, depart_name);
                list.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
        
        return list;
    }
    public int getNextID_Department() throws SQLException {
        int nextID = 0;
        String sql = "{ ? = CALL GetMaxDepartId() }";
        CallableStatement cstmt = con.prepareCall(sql);
        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
        cstmt.execute();
        nextID = cstmt.getInt(1) + 1;
        cstmt.close();
        return nextID;
    }


    
    // Insert
    public void insertDepartment(DepartmentsDTO data) throws SQLException {
        String sql = "{CALL sp_InsertDepartment(?, ?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1, data.getDepart_id());
            cstmt.setString(2, data.getDepart_name());
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Delete
    public void deleteDepartment(String depart_id) throws SQLException {
        String sql = "{CALL sp_DeleteDepartment(?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1, depart_id);
            cstmt.execute();
        } catch (SQLException e) {
            // Log the exception
            e.printStackTrace();
            throw e;
        }
    }

    // Update
    public void updateDepartment(DepartmentsDTO data) throws SQLException {
        String sql = "{CALL sp_UpdateDepartment(?, ?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1, data.getDepart_id());
            cstmt.setString(2, data.getDepart_name());
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
}
