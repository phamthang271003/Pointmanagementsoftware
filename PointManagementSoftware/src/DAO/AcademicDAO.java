package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.AcademicDTO;

public class AcademicDAO {
    private Connection con;

    public AcademicDAO() {
        DataProvider.getInstance().connectToDatabase();
        con = DataProvider.getInstance().getConnection();
    }

    public ArrayList<AcademicDTO> listAcademic() throws SQLException {
        ArrayList<AcademicDTO> list = new ArrayList<>();
        String sql = "{call sp_GetAcademic}";
        
        try (CallableStatement cstmt = con.prepareCall(sql);
             ResultSet r = cstmt.executeQuery()) {
             
            while (r.next()) {
                String aca_id = r.getString(1);
                String aca_name = r.getString(2);
                String aca_semester = r.getString(3);
                String depart_id = r.getString(4);
                AcademicDTO data = new AcademicDTO(aca_id, aca_name,aca_semester,depart_id);
                list.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
        
        return list;
    }
    public int getNextID_Academic() throws SQLException {
        int nextID = 0;
        String sql = "{ ? = CALL GetMaxAcademyId() }";
        CallableStatement cstmt = con.prepareCall(sql);
        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
        cstmt.execute();
        nextID = cstmt.getInt(1) + 1;
        cstmt.close();
        return nextID;
    }


    
    // Insert
    public void insertAcademic(AcademicDTO data) throws SQLException {
        String sql = "{CALL InsertAcademicDiscipline(?,?,?,?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1, data.getAca_id());
            cstmt.setString(2, data.getAca_name());
            cstmt.setString(3, data.getAca_semester());
            cstmt.setString(4, data.getDepart_id());
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Delete
    public void deleteAcademic(String aca_id) throws SQLException {
        String sql = "{CALL DeleteAcademic(?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1, aca_id);
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Update
    public void updateAcademic(AcademicDTO data) throws SQLException {
        String sql = "{CALL UpdateAcademicDiscipline(?,?,?,?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) {
            System.out.println("Updating Academic:");
            System.out.println("ID: " + data.getAca_id());
            System.out.println("Name: " + data.getAca_name());
            System.out.println("Semester: " + data.getAca_semester());
            System.out.println("Department ID: " + data.getDepart_id());
            
            cstmt.setString(1, data.getAca_id());
            cstmt.setString(2, data.getAca_name());
            cstmt.setString(3, data.getAca_semester());
            cstmt.setString(4, data.getDepart_id());
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

  

}
