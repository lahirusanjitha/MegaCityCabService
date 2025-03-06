/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.VehicleDAO;
import Model.Vehicle;
import java.util.List;


/**
 *
 * @author Lahiru Sanjitha
 */
public class VehicleService {
    private final VehicleDAO vehicleDAO = new VehicleDAO();
    
    public boolean addVehicle(Vehicle vehicle) {
        return vehicleDAO.addVehicle(vehicle);
    }
    public List<Vehicle> getAllVehicles(){
        return vehicleDAO.getAllVehicles();
    }
    public Vehicle getVehicleById(int vehicleId){
        return vehicleDAO.getVehicleById(vehicleId);
    }
    
    public boolean updateVehicleStatus(int vehicleId, String status) {
        Vehicle vehicle = vehicleDAO.getVehicleById(vehicleId);
        if (vehicle == null) {
            return false;
        }
        return vehicleDAO.updateVehicleStatus(vehicleId, status);
    }
        public boolean deleteVehicleById(int vehicleId) {
        return vehicleDAO.deleteVehicleById(vehicleId);
    }
}
