/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBUtil;
import Model.Bill;
import Service.BillService;
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
public class BillDAO {
    private BillService billService;
    private Connection connection;
    
    public BillDAO(){
    connection = DBUtil.getInstance().getConnection();
    }
    
    public boolean createBill(int bookingId, double totalFare, Bill bill) {
        String query = "INSERT INTO bill (bookingId, baseFare, distance, ratePerKm, duration, ratePerMinute, additionalCharges, taxRate, totalFare) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            stmt.setDouble(2, bill.getBaseFare());
            stmt.setDouble(3, bill.getDistance());
            stmt.setDouble(4, bill.getRatePerKm());
            stmt.setDouble(5, bill.getDuration());
            stmt.setDouble(6, bill.getRatePerMinute());
            stmt.setDouble(7, bill.getAdditionalCharges());
            stmt.setDouble(8, bill.getTaxRate());
            stmt.setDouble(9, totalFare);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM bill";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                bills.add(mapBill(rs)); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
    
    public Bill getBillByBookingId(int bookingId) {
        String query = "SELECT * FROM bill WHERE bookingId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("billId"));
                bill.setBookingId(rs.getInt("bookingId"));
                bill.setBaseFare(rs.getInt("baseFare"));
                bill.setDistance(rs.getDouble("distance"));  
                bill.setRatePerKm(rs.getDouble("ratePerKm"));
                bill.setDuration(rs.getDouble("duration"));
                bill.setRatePerMinute(rs.getDouble("ratePerMinute"));
                bill.setAdditionalCharges(rs.getDouble("additionalCharges"));
                bill.setTaxRate(rs.getDouble("taxRate"));
                bill.setTotalFare(rs.getDouble("totalFare"));
                
                return bill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     public boolean deleteBill(int billId) {
        String query = "DELETE FROM bill  WHERE billId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, billId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
        private Bill mapBill(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
            bill.setBillId(rs.getInt("billId"));
            bill.setBookingId(rs.getInt("bookingId"));
            bill.setBaseFare(rs.getDouble("baseFare")); 
            bill.setDistance(rs.getDouble("distance"));
            bill.setRatePerKm(rs.getDouble("ratePerKm"));
            bill.setDuration(rs.getDouble("duration"));
            bill.setRatePerMinute(rs.getDouble("ratePerMinute"));
            bill.setAdditionalCharges(rs.getDouble("additionalCharges"));
            bill.setTaxRate(rs.getDouble("taxRate"));
            bill.setTotalFare(rs.getDouble("totalFare"));
        return bill;
    }
    
}



