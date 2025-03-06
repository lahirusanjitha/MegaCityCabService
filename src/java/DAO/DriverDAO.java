/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBUtil;
import Model.Driver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lahiru Sanjitha
 */
public class DriverDAO {
    private final Connection connection;
    
       public DriverDAO() {
        connection = DBUtil.getInstance().getConnection();
      }
       
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM driver";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                drivers.add(mapDriver(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
       
    
        public boolean addDriver(Driver driver) {
        String query = "INSERT INTO driver (name, licenseNumber, phone, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getPhone());
            stmt.setString(4, driver.getStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
        public Driver getDriverById(int driverId) {
        String query = "SELECT * FROM driver WHERE driverId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapDriver(rs); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

       public boolean updateDriverStatus(int driverId, String status) {
        String query = "UPDATE driver SET status = ? WHERE driverId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, driverId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
       
       public boolean deleteDriverById(int driverId) {
        String query = "DELETE FROM driver WHERE driverId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, driverId);
    
            int rowsAffected = stmt.executeUpdate();  
            return rowsAffected > 0; 

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        private Driver mapDriver(ResultSet rs) throws SQLException {
        Driver driver = new Driver();
        driver.setDriverId(rs.getInt("driverId"));
        driver.setName(rs.getString("name"));
        driver.setLicenseNumber(rs.getString("licenseNumber"));
        driver.setPhone(rs.getString("phone"));
        driver.setStatus(rs.getString("status"));
        return driver;
    }
}
