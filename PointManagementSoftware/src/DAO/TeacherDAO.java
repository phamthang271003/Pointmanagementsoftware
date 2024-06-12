package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.TeacherDTO;

public class TeacherDAO {

	private Connection con;
	
	public TeacherDAO() {
		con = DataProvider.getInstance().getConnection();
	}
	
	public String[] takeInforDeparts() {
		ArrayList<String> classInfoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "EXEC takeInforDepart";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				classInfoList.add(rs.getString("DepartInfo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return classInfoList.toArray(new String[0]);
	}
	
	public List<TeacherDTO> takeAllTeachers() {
        List<TeacherDTO> teacherList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "EXEC takeAllTeacher";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String teaId = rs.getString("tea_id");
                String teaName = rs.getString("tea_name");
                String teaSex = rs.getString("tea_sex");
                String departId = rs.getString("depart_id");
                TeacherDTO teacher = new TeacherDTO(teaId, teaName, teaSex, departId);
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return teacherList;
    }
	
	public List<TeacherDTO> takeAllTeachersByDepartID(String departId) {
	    List<TeacherDTO> teacherList = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String sql = "EXEC takeTeacherById ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, departId);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            String teaId = rs.getString("tea_id");
	            String teaName = rs.getString("tea_name");
	            String teaSex = rs.getString("tea_sex");
	            TeacherDTO teacher = new TeacherDTO(teaId, teaName, teaSex, departId);
	            teacherList.add(teacher);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return teacherList;
	}
	
	public void insertTeacher(String teaId, String teaName, String teaSex, String departId) {
	    PreparedStatement pstmt = null;

	    try {
	        String sql = "EXEC insertTeacher ?, ?, ?, ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, teaId);
	        pstmt.setString(2, teaName);
	        pstmt.setString(3, teaSex);
	        pstmt.setString(4, departId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public int checkIdTeacher(String id_teacher)
	{
		int result = 0;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String sql = "EXEC checkIdTeacher ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, id_teacher);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            result = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return result;
	}
	
	public void updateTeacher(String teaId, String teaName, String teaSex, String departId) {
	    PreparedStatement pstmt = null;

	    try {
	        String sql = "EXEC updateTeacher ?, ?, ?, ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, teaId);
	        pstmt.setString(2, teaName);
	        pstmt.setString(3, teaSex);
	        pstmt.setString(4, departId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void deleteTeacher(String id_teacher) {
	    PreparedStatement pstmt = null;

	    try {
	        String sql = "EXEC deleteTeacher ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, id_teacher);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public ArrayList<TeacherDTO> getTeachersByAcademic(String academicID) {
        ArrayList<TeacherDTO> list = new ArrayList<>();
        try {
                MysqlAccess helper = new MysqlAccess();
                helper.open();
                CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getTeacherToDeparment(?)}");
                stmt.setString(1, academicID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                        TeacherDTO teacher = new TeacherDTO();
                        teacher.setTeaId(rs.getString("tea_id"));
                        teacher.setTeaName(rs.getString("tea_name"));

                        list.add(teacher);
                }
        } catch (Exception ex) {
                System.out.println(ex.getMessage());
        }
        return list;
}
	
}
