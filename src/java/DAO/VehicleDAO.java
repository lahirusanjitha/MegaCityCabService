/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBUtil;
import Model.Vehicle;
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
public class VehicleDAO {
    private final Connection connection;
    
    public VehicleDAO(){
    connection = DBUtil.getInstance().getConnection();
    }
    
    public boolean addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicle (plateNumber, model, type, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicle.getPlateNumber());
            stmt.setString(2, vehicle.getModel());
            stmt.setString(3, vehicle.getType());
            stmt.setString(4, vehicle.getStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicle";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vehicles.add(mapVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public Vehicle getVehicleById(int vehicleId) {
        String query = "SELECT * FROM vehicle WHERE vehicleId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapVehicle(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateVehicleStatus(int vehicleId, String status) {
        String query = "UPDATE vehicle SET status = ? WHERE vehicleId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, vehicleId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteVehicleById(int vehicleId){
        String query = "DELETE FROM vehicle WHERE vehicleId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicleId);
    
            int rowsAffected = stmt.executeUpdate();  
            return rowsAffected > 0; 

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private Vehicle mapVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(rs.getInt("vehicleId"));
        vehicle.setPlateNumber(rs.getString("plateNumber"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setType(rs.getString("type"));
        vehicle.setStatus(rs.getString("status"));
        return vehicle;
    }
}
