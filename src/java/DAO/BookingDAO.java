/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBUtil;
import Model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lahiru Sanjitha
 */
public class BookingDAO {
        private final Connection connection;
    
       public BookingDAO() {
        connection = DBUtil.getInstance().getConnection();
      }
       
       public boolean addBooking(Booking booking) {
        String query = "INSERT INTO booking (customerId, pickupLocation, dropoffLocation, bookingDate, driverId, vehicleId, totalFare) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, booking.getCustomerId());
            stmt.setString(2, booking.getPickupLocation());
            stmt.setString(3, booking.getDropoffLocation());
            stmt.setTimestamp(4, Timestamp.valueOf(booking.getBookingDate()));
            stmt.setInt(5, booking.getDriverId());
            stmt.setInt(6, booking.getVehicleId());
            stmt.setBigDecimal(7, booking.getTotalFare());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
       
       public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                bookings.add(mapBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
       public Booking getBookingById(int bookingId) {
        String query = "SELECT * FROM booking WHERE bookingId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapBooking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

       public boolean updateBooking(int bookingId,Booking booking) {
        String query = "UPDATE booking SET pickupLocation = ?, dropoffLocation = ?, bookingDate = ?, driverId = ?, vehicleId = ?, totalFare = ? WHERE bookingId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, booking.getPickupLocation());
            stmt.setString(2, booking.getDropoffLocation());
            stmt.setTimestamp(3, Timestamp.valueOf(booking.getBookingDate()));
            stmt.setInt(4, booking.getDriverId());
            stmt.setInt(5, booking.getVehicleId());
            stmt.setBigDecimal(6, booking.getTotalFare());
            stmt.setInt(7, booking.getBookingId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cancelBooking(int bookingId) {
        String query = "UPDATE booking SET status = 'Cancelled' WHERE bookingId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteBooking(int bookingId) {
        String query = "DELETE FROM booking  WHERE bookingId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

       
       private Booking mapBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getInt("bookingId"));
        booking.setCustomerId(rs.getInt("customerId"));
        booking.setPickupLocation(rs.getString("pickupLocation"));
        booking.setDropoffLocation(rs.getString("dropoffLocation"));
        booking.setBookingDate(rs.getTimestamp("bookingDate").toLocalDateTime());
        booking.setStatus(rs.getString("status"));
        booking.setDriverId(rs.getInt("driverId"));
        booking.setVehicleId(rs.getInt("vehicleId"));
        booking.setTotalFare(rs.getBigDecimal("totalFare"));
        return booking;
    }
}
