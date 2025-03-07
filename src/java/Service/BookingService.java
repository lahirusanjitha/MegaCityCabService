/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.BookingDAO;
import Model.Booking;
import java.util.List;

/**
 *
 * @author Lahiru Sanjitha
 */
public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAO();
    
    public boolean addBooking(Booking booking){
        return bookingDAO.addBooking(booking);
    }
    public List<Booking> getAllBookings(){
        return bookingDAO.getAllBookings();
    }
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }  
    public boolean cancelBooking(int bookingId) {
        return bookingDAO.cancelBooking(bookingId);
    }
    public boolean updateBooking(int bookingId, Booking booking) {
        return bookingDAO.updateBooking(bookingId, booking);
    }
    public boolean deleteBooking(int bookingId) {
        return bookingDAO.deleteBooking(bookingId);
    }
    
}
