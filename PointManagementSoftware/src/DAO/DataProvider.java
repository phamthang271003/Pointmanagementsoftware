package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataProvider {
	private static DataProvider instance;
	private Connection connection;

	// trả về instance duy nhất của lớp
	public static DataProvider getInstance() {
		if (instance == null) {
			instance = new DataProvider();
		}
		return instance;
	}

//khong cho tao instance moi tu ben ngoai
	private DataProvider() {

	}

//Thực hiện kết nối tới Database
	public void connectToDatabase() {
		String strServer = "LAPTOP-5KVBJE6O\\SQLEXPRESS";
		String strDatabase = "db_QLDiemSV";

		String userName = "sa";
		String passWord = "123";
		try {
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(driver);
			String connectionURL = "jdbc:sqlserver://" + strServer + ":1433;databaseName=" + strDatabase
					+ ";encrypt=true;trustServerCertificate=true";
			connection = DriverManager.getConnection(connectionURL, userName, passWord);

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		if(connection != null) {
			System.out.println("Kết nối cơ sỡ dữ liệu thành công");
		}else {
			System.out.println("Kết nối cơ sỡ dữ liệu thất bại");
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
