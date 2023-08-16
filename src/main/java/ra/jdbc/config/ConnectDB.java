package ra.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// lớp thực hiện đóng/mở kết nối tới cơ sở dữ liệu
public class ConnectDB {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/qlbh";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "18061999";

    //  mở 1 kết nối tới csdl
    public static Connection getConnection() {
        try {
            // khai báo class kết nối tới csdl
            Class.forName(DRIVER);
            // tạo kết nối
            Connection  conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return  conn;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void closeConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
