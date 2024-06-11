package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.AcademicDTO;
import DTO.SubjectDTO;

public class SubjectDAO {
	private Connection con;

	public SubjectDAO() {
		DataProvider.getInstance().connectToDatabase();
		con = DataProvider.getInstance().getConnection();
	}

	public ArrayList<SubjectDTO> listSubject() throws SQLException {
		ArrayList<SubjectDTO> list = new ArrayList<>();
		String sql = "{call sp_GetSubject}";

		try (CallableStatement cstmt = con.prepareCall(sql); ResultSet r = cstmt.executeQuery()) {

			while (r.next()) {
				String sub_id = r.getString(1);
				String sub_name = r.getString(2);
				String sub_semester = r.getString(3);
				SubjectDTO data = new SubjectDTO(sub_id, sub_name, sub_semester);
				list.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	  public int getNextID_Subject() throws SQLException {
	        int nextID = 0;
	        String sql = "{ ? = CALL GetMaxSubjectId() }";
	        CallableStatement cstmt = con.prepareCall(sql);
	        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
	        cstmt.execute();
	        nextID = cstmt.getInt(1) + 1;
	        cstmt.close();
	        return nextID;
	    }
	
	// Insert
    public void insertSubject(SubjectDTO data) throws SQLException {
        String sql = "{CALL InsertSubject(?,?,?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1, data.getSub_id());
            cstmt.setString(2, data.getSub_name());
            cstmt.setString(3, data.getSub_semester());
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
 // Delete
    public void deleteSubject(String sub_id) throws SQLException {
        String sql = "{CALL DeleteSubject(?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1,sub_id);
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    //Update
    public void updateSubjects(SubjectDTO data) throws SQLException {
        String sql = "{CALL UpdateSubject(?,?,?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) { 
            cstmt.setString(1, data.getSub_id());
            cstmt.setString(2, data.getSub_name());
            cstmt.setString(3, data.getSub_semester());
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
