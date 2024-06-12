package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataProvider {
	private static DataProvider instance;
	private Connection connection;

	public static DataProvider getInstance() {
		if (instance == null) {
			instance = new DataProvider();
		}
		instance.connectToDatabase();
		return instance;
	}

	private DataProvider() {

	}

	public void connectToDatabase() {
		String strServer = "DESKTOP-MIJIJRH";
		String strDatabase = "db_QLDiemSV";

		String userName = "sa";
		String passWord = "123";
		try {
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(driver);
			String connectionURL = "jdbc:sqlserver://" + strServer + ":1433;databaseName=" + strDatabase
					+ ";encrypt=true;trustServerCertificate=true";
			setConnection(DriverManager.getConnection(connectionURL, userName, passWord));

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		if(getConnection() != null) {
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
