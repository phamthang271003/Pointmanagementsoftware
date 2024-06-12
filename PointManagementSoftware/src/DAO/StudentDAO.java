package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.StudentsDTO;
import DTO.StudyRecordDTO;

public class StudentDAO {

	private Connection con;

	public StudentDAO() {
		con = DataProvider.getInstance().getConnection();
	}

	public String[] takeInforClasses() {
		ArrayList<String> classInfoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "EXEC takeInforClass";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				classInfoList.add(rs.getString("ClassInfo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return classInfoList.toArray(new String[0]);
	}

	public String[] takeInforAca() {
		ArrayList<String> classInfoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "EXEC takeInforAca";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				classInfoList.add(rs.getString("AcaInfo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return classInfoList.toArray(new String[0]);
	}

	public int checkStudentId(String idStudent) {
		int result = 0; // Mặc định là 0 nếu không tìm thấy sinh viên

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "EXEC checkStudentId ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idStudent);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("Result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public void insertStudent(String stu_id, String stu_name, String stu_day_entry, String stu_semester, String stu_sex,
			String stu_dob, String stu_address, String aca_id, String class_id) {
		PreparedStatement pstmt = null;

		try {
			String sql = "EXEC insertStudent ?, ?, ?, ?, ?, ?, ?, ?, ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stu_id);
			pstmt.setString(2, stu_name);
			pstmt.setString(3, stu_day_entry);
			pstmt.setString(4, stu_semester);
			pstmt.setString(5, stu_sex);
			pstmt.setString(6, stu_dob);
			pstmt.setString(7, stu_address);
			pstmt.setString(8, aca_id);
			pstmt.setString(9, class_id);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//
	private ArrayList<StudentsDTO> students;

	public ArrayList<StudentsDTO> menuStudent() {
		ArrayList<StudentsDTO> list = new ArrayList<>();
		try {
			MysqlAccess helper = new MysqlAccess();
			helper.open();
			CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAllStudents}");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StudentsDTO stu = new StudentsDTO();
				stu.setStu_id(rs.getString("stu_id"));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_day_entry(rs.getDate("stu_day_entry"));
				stu.setStu_semester(rs.getInt("stu_semester"));
				stu.setStu_sex(rs.getString("stu_sex"));
				stu.setDob(rs.getDate("stu_dob"));
				stu.setAddress(rs.getString("stu_address"));
				stu.setAca_id(rs.getString("aca_name"));
				stu.setClass_id(rs.getString("class_name"));
				list.add(stu);
			}
			helper.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}

	public boolean isStudentIdExist(String stuId) {
		try {
			MysqlAccess helper = new MysqlAccess();
			helper.open();
			CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getStudentById(?)}");
			stmt.setString(1, stuId);
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

	public boolean addStudent(StudentsDTO stu) {
		try {
			MysqlAccess helper = new MysqlAccess();
			helper.open();
			CallableStatement stmt = helper.getConnection().prepareCall("{call sp_insertStudent(?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, stu.getStu_id());
			stmt.setString(2, stu.getStu_name());
			stmt.setDate(3, stu.getStu_day_entry());
			stmt.setInt(4, stu.getStu_semester());
			stmt.setString(5, stu.getStu_sex());
			stmt.setDate(6, stu.getDob());
			stmt.setString(7, stu.getAddress());
			stmt.setString(8, stu.getAca_id());
			stmt.setString(9, stu.getClass_id());
			stmt.executeUpdate();
			helper.close();
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public boolean updateStudent(StudentsDTO stu) {
		try {
			MysqlAccess helper = new MysqlAccess();
			helper.open();
			CallableStatement stmt = helper.getConnection().prepareCall("{call sp_updateStudent(?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, stu.getStu_id());
			stmt.setString(2, stu.getStu_name());
			stmt.setDate(3, stu.getStu_day_entry());
			stmt.setInt(4, stu.getStu_semester());
			stmt.setString(5, stu.getStu_sex());
			stmt.setDate(6, stu.getDob());
			stmt.setString(7, stu.getAddress());
			stmt.setString(8, stu.getAca_id());
			stmt.setString(9, stu.getClass_id());
			stmt.executeUpdate();
			helper.close();
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public boolean deleteStudent(String stuId) {
		try {
			MysqlAccess helper = new MysqlAccess();
			helper.open();
			CallableStatement stmt = helper.getConnection().prepareCall("{call sp_deleteStudent(?)}");
			stmt.setString(1, stuId);
			stmt.executeUpdate();
			helper.close();
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public StudentsDTO searchStudent(String stuId) {
		StudentsDTO stu = null;
		try {
			MysqlAccess helper = new MysqlAccess();
			helper.open();
			CallableStatement stmt = helper.getConnection().prepareCall("{call findIDStudent(?)}");
			stmt.setString(1, stuId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				stu = new StudentsDTO();
				stu.setStu_id(rs.getString("stu_id"));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_day_entry(rs.getDate("stu_day_entry"));
				stu.setStu_semester(rs.getInt("stu_semester"));
				stu.setStu_sex(rs.getString("stu_sex"));
				stu.setDob(rs.getDate("stu_dob"));
				stu.setAddress(rs.getString("stu_address"));
				stu.setAca_id(rs.getString("aca_name"));
				stu.setClass_id(rs.getString("class_name"));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return stu;
	}

	public ArrayList<StudyRecordDTO> getStudyRecords(String studentId) {
		ArrayList<StudyRecordDTO> studyRecords = new ArrayList<>();
		try {
			MysqlAccess helper = new MysqlAccess();
			helper.open();
			CallableStatement stmt = helper.getConnection().prepareCall("{call sp_studyRecords(?)}");
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StudyRecordDTO record = new StudyRecordDTO();
				record.setStu_id(rs.getString("stu_id"));
				record.setStu_name(rs.getString("stu_name"));
				record.setSub_name(rs.getString("sub_name"));
				record.setRes_time(rs.getInt("res_time"));
				record.setRes_score(rs.getFloat("res_score"));
				studyRecords.add(record);
			}
			helper.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return studyRecords;
	}

}
