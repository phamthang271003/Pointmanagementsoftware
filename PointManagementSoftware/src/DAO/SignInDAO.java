package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignInDAO {
	
	private Connection con;
	
	public SignInDAO() {
		con = DataProvider.getInstance().getConnection();
	}

	
	public int checkAccount(String username,String password)
	{
		int result = 0; // Mặc định là 0 nếu không tìm thấy tài khoản
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "EXEC checkAccount ?, ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                result = rs.getInt("Result");
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
	
	public int checkRoleAccount(String username)
	{
		int result = 0; // Mặc định là 0 nếu không tìm thấy tài khoản
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "EXEC checkRoleAccount ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                result = rs.getInt("RoleResult");
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
}
