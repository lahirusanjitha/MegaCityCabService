/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBUtil;
import Model.Bill;
import Service.BillService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    
//public byte[] generateBillPDF(int billId) throws DocumentException {
//    Bill bill = billService.getBillByBookingId(billId);
//    if (bill == null) {
//        return null;  
//    }
//
//    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//        Document document = new Document();
//        PdfWriter writer = PdfWriter.getInstance(document, outputStream); 
//        document.open(); 
//
//        document.add(new Paragraph("Mega City Cab - Bill"));
//        document.add(new Paragraph("Bill ID: "));
//        document.add(new Paragraph("Booking ID:"));
//        document.add(new Paragraph("Total Fare: $"));
//        document.add(new Paragraph("Tax: $"));
//        document.add(new Paragraph("Grand Total: $"));
//
//        document.close(); 
//
//        return outputStream.toByteArray(); 
//    } catch (IOException e) {
//        e.printStackTrace();
//        return null;
//    }
//
//}
}



