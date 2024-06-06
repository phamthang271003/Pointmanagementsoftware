package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ClassesDTO;

public class ClassesDAO {
private Connection con;

public ClassesDAO() {
	con = DataProvider.getInstance().getConnection();
}
public ArrayList<ClassesDTO> menuClass() throws SQLException {
    ArrayList<ClassesDTO> list = new ArrayList<>();
    String sql = "SELECT * FROM [classes]";
    PreparedStatement p = con.prepareStatement(sql);
    ResultSet r = p.executeQuery();
    while (r.next()) {
        String class_id = r.getString(1);
        String class_name = r.getString(2);
        ClassesDTO data = new ClassesDTO(class_id, class_name);
        list.add(data);
    }
    r.close();
    p.close();
    return list;
}
}
