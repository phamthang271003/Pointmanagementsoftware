package DAO;

import java.sql.*;

public class MysqlAccess {
        String strServer = "DESKTOP-MIJIJRH";
        String strDatbaseName = "db_QLDiemSV";
        private Connection connection;
        public Connection getConnection() {
                return this.connection;
        }

        public void open() {
                try {
                        java.lang.Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        String url = "jdbc:sqlserver://" + strServer + ":1433;user=sa;password=123;databaseName=" + strDatbaseName + ";encrypt=true;trustServerCertificate=true";
                        connection = DriverManager.getConnection(url);
                } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Không kết nối được với database.");
                }
        }
        public void close() {
                try {
                        this.connection.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }



}
