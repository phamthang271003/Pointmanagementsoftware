package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.ResultDTO;

public class ResultDAO {
    private Connection con;

    public ResultDAO() {
        DataProvider.getInstance().connectToDatabase();
        con = DataProvider.getInstance().getConnection();
    }

    public ArrayList<ResultDTO> getResultsByClassId(int classId) throws SQLException {
        ArrayList<ResultDTO> results = new ArrayList<>();
        String sql = "{call GetResultsByClassId(?)}";

        try (CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setInt(1, classId);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    ResultDTO result = new ResultDTO();
                    result.setStu_id(rs.getString("stu_id"));
                    result.setStu_name(rs.getString("stu_name"));
                    result.setSub_name(rs.getString("sub_name"));
                    result.setRes_time(rs.getInt("res_time"));
                    result.setRes_score(rs.getFloat("res_score"));
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return results;
    }
    
    public void insertResult(ResultDTO data) throws SQLException {
        String sql = "{CALL InsertResult(?,?)}";
        
        try (CallableStatement cstmt = con.prepareCall(sql)) {
        	cstmt.setString(1, data.getSub_id());
        	cstmt.setString(2, data.getStu_id());

            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
