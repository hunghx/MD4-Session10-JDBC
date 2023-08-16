package ra.jdbc;

import ra.jdbc.config.ConnectDB;

import java.sql.*;

public class Main {
    public static final String SQL = "CREATE TABLE CUSTOMERS(id int auto_increment primary key"+
            ",name varchar(50) , age int )";
    public static final String SQL2 = "INSERT INTO CUSTOMERS(NAME,AGE) VALUES('Nguyễn Văn A',18)";
    public static final String SQL3 = "SELECT * FROM CUSTOMERS";
    public static final String SQL4 = "SELECT * FROM CUSTOMERS WHERE id = ?";
    public static final String SQL5 = "DELETE FROM CUSTOMERS WHERE id = ?";



    public static void main(String[] args) {
        Connection con = ConnectDB.getConnection();
        System.out.println(con);
// 3 interface : Statement , PreparedStatement, CallableStatement
        // Statement : chỉ thực hiện các câu sql tĩnh
        // PreparedStatement :ko chỉ thực hiện các câu sql tĩnh, mà còn hỗ trợ câu sql có tham số
        // CallableStatement : còn hỗ trợ cả gọi stored procerdure
        try {
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(SQL3);
//            while (rs.next()){
//                System.out.println("ID : "+rs.getInt("id"));
//                System.out.println("NAME : "+rs.getString("name"));
//                System.out.println("AGE : "+rs.getInt("age"));
//            }
//            PreparedStatement pre = con.prepareStatement(SQL5);
//            pre.setInt(1,1);
//            ResultSet rs = pre.executeQuery();
//            while (rs.next()){
//                System.out.println("ID : "+rs.getInt("id"));
//                System.out.println("NAME : "+rs.getString("name"));
//                System.out.println("AGE : "+rs.getInt("age"));
//            }

//            pre.setInt(1,2);
//            pre.executeUpdate();

            CallableStatement call = con.prepareCall("{call GET_ALL_CUSTOMER()}");
//            call.setString(2, "Hồ Xuân Hương");
//            call.setInt(3,22);
//            call.setInt(1,3);
//            call.executeUpdate();
            ResultSet rs=call.executeQuery();
            while (rs.next()){
                System.out.println("ID : "+rs.getInt("id"));
                System.out.println("NAME : "+rs.getString("name"));
                System.out.println("AGE : "+rs.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
    }
}
