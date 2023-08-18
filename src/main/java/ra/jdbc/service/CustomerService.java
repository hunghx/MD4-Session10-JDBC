package ra.jdbc.service;

import ra.jdbc.config.ConnectDB;
import ra.jdbc.model.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    public List<Customer> findByPageAndSize(int page, int size){
        Connection conn = ConnectDB.getConnection();
        List<Customer> customers = new ArrayList<>();
        try{
            CallableStatement call = conn.prepareCall("{call getListCustomerByPageAndSize(?,?)}");
            call.setInt(1,page);
            call.setInt(2,size);
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                customers.add(c);
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return customers;
    }
    public Customer findById (int id){
        Connection conn = ConnectDB.getConnection();
        Customer c =null;
        try{
            CallableStatement call = conn.prepareCall("{call GET_CUSTOMER_BY_ID(?)}");
            call.setInt(1,id);
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return c;
    }

    public void save(Customer customer){
        Connection conn = ConnectDB.getConnection();
        CallableStatement call =null;
        try{
           if(customer.getId() ==0){
               // chức năng thêm mới
               call = conn.prepareCall("{call INSERT_CUSTOMER(?,?)}");
               call.setString(1,customer.getName());
               call.setInt(2,customer.getAge());
               call.executeUpdate();
           }else {
//               chức năng cập nhật
               call = conn.prepareCall("{call UPDATE_CUSTOMER(?,?,?)}");
               call.setString(2,customer.getName());
               call.setInt(3,customer.getAge());
               call.setInt(1,customer.getId());
               call.executeUpdate();
           }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
    public void delete(int id){
        Connection conn = ConnectDB.getConnection();
        try{
            CallableStatement call = conn.prepareCall("{call DELETE_CUSTOMER(?)}");
            call.setInt(1,id);
            call.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
