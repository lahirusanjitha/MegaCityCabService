/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBUtil;
import Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Lahiru Sanjitha
 */
public class CustomerDAO {

    private final Connection connection;
    
    public CustomerDAO(){
        connection = DBUtil.getInstance().getConnection();
    }
    
        public boolean registerCustomer(Customer customer) {
        try {
            String hashedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt(12));
            
            String query = "INSERT INTO customer (name,userName,address,nic,tel, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getUserName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNic());
            stmt.setString(5, customer.getTel());
            stmt.setString(6, hashedPassword);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   
 public boolean validateLogin(String userName, String password) {
        try {
            String query = "SELECT password FROM customer WHERE userName = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password"); 
                return BCrypt.checkpw(password, hashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 
  public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(mapCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
  
    public Customer getCustomerById(int customerId) {
        String query = "SELECT * FROM customer WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapCustomer(rs); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateCustomer(int customerId,Customer customer) {
        String query = "UPDATE customer SET name = ?, address = ?, nic = ?, tel = ? WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getNic());
            stmt.setString(4, customer.getTel());
            stmt.setInt(5, customer.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteCustomerById(int customerId) {
        String query = "DELETE FROM customer WHERE driverId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
    
            int rowsAffected = stmt.executeUpdate();  
            return rowsAffected > 0; 

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

      private Customer mapCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("customerId"));
        customer.setName(rs.getString("name"));
//        customer.setUserName(rs.getString("userName"));
        customer.setAddress(rs.getString("address"));
        customer.setNic(rs.getString("nic"));
        customer.setTel(rs.getString("tel"));
        return customer;
    }


    
}
