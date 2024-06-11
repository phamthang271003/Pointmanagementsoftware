package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.ExamRoomDTO;
import DTO.ExamSchedulesDTO;
import DTO.SubjectsDTO;

public class ExamRoomDAO {
	private Connection conn;
	public ExamRoomDAO() {
		this.conn = DataProvider.getInstance().getConnection();
	}
	public List<ExamRoomDTO> getAll() throws SQLException{
		List<ExamRoomDTO> lstERoom = new ArrayList<ExamRoomDTO>();
		String query = "{call getExamRoom}";
		CallableStatement call = conn.prepareCall(query);
		ResultSet rs = call.executeQuery();
		while(rs.next())
		{
			ExamRoomDTO e = new ExamRoomDTO();
			e.setRoom_id(rs.getString(1));
			e.setRoom_name(rs.getString(2));
			e.setRoom_quantity(3);
			lstERoom.add(e);
		
		}
		return lstERoom;
	}
}
