package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.AcademicDTO;
import DTO.ClassesDTO;

public class ClassesDAO {
	private Connection con;

	public ClassesDAO() {
		DataProvider.getInstance().connectToDatabase();
		con = DataProvider.getInstance().getConnection();
	}
	public ArrayList<ClassesDTO> listClass() throws SQLException {
        ArrayList<ClassesDTO> list = new ArrayList<>();
        String sql = "{call GetClassName}";
        
        try (CallableStatement cstmt = con.prepareCall(sql);
             ResultSet r = cstmt.executeQuery()) {
             
            while (r.next()) {
                String class_id = r.getString(1);
                String class_name = r.getString(2);
                ClassesDTO data = new ClassesDTO(class_id,class_name);
                list.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
        
        return list;
    }
}
