/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.DriverDAO;
import Model.Driver;
import java.util.List;

/**
 *
 * @author Lahiru Sanjitha
 */
public class DriverService {
    private DriverDAO driverDAO = new DriverDAO();

    public boolean addDriver(Driver driver) {
        return driverDAO.addDriver(driver);
    }
    public List<Driver> getAllDrivers() {
        return driverDAO.getAllDrivers();
    }
    public Driver getDriverById(int driverId) {
        return driverDAO.getDriverById(driverId);
    }
    public boolean updateDriverStatus(int driverId, String status) {
        Driver driver = driverDAO.getDriverById(driverId);
        if (driver == null) {
            System.out.println("Driver not found!");
            return false;
        }
        return driverDAO.updateDriverStatus(driverId, status);
    }
    public boolean deleteDriverById(int driverId) {
        return driverDAO.deleteDriverById(driverId);
    }

}
