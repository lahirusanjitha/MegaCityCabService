/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Booking;
import Service.BookingService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Lahiru Sanjitha
 */

@Path("/booking")  
@Produces(MediaType.APPLICATION_JSON)  
@Consumes(MediaType.APPLICATION_JSON)
public class BookingServiceResource {
    private final BookingService bookingSevice = new BookingService();
    
    @POST
    @Path("/addBooking")
    public Response addBooking(Booking booking) {
        boolean isCreated = bookingSevice.addBooking(booking);
        if (isCreated) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Booking added successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to add Booking\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
    @GET
    @Path("/getAllBookings")
    public Response getAllBookings() {
        List<Booking> bookings = bookingSevice.getAllBookings();
        return Response.ok(bookings).build();
    }
    @GET
    @Path("getBookingById/{bookingId}")
    public Response getBookingById(@PathParam("bookingId") int bookingId) {
        Booking booking = bookingSevice.getBookingById(bookingId);
        if (booking != null) {
            return Response.ok(booking).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Booking not found").build();
        }
    }
    
@GET
@Path("getBookingByCustId/{customerId}")
public Response getBookingByCustId(@PathParam("customerId") int customerId) {
        Booking bookings = bookingSevice.getBookingsByCustId(customerId); // Returns a list instead of a single object
    if (bookings != null) {
        return Response.ok(bookings).build(); 
    } else {
        return Response.status(Response.Status.NOT_FOUND).entity("No bookings found").build();
    }
}
    
    @GET
    @Path("/total")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTotalBookings() {
        int totalBookings = bookingSevice.getTotalBookings();
        Map<String, Integer> response = new HashMap<>();
        response.put("totalBookings", totalBookings);
        return Response.ok(response).build();
    }
    

    @PUT
    @Path("updateBooking/{bookingId}")
    public Response updateBooking(@PathParam("bookingId") int bookingId, Booking booking) {
        if (booking == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid booking data").build();
        }

        booking.setBookingId(bookingId);
        boolean isUpdated = bookingSevice.updateBooking(bookingId , booking);

        if (isUpdated) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Booking Updated successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to Update Booking\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }

    @PUT
    @Path("cancelBooking/{bookingId}")
    public Response cancelBooking(@PathParam("bookingId") int bookingId) {
        boolean isCanceled = bookingSevice.cancelBooking(bookingId);
        if (isCanceled) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Booking Caneled successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to Cancel Booking\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
    
    @DELETE
    @Path("deleteBooking/{id}")
    public Response deleteBooking(@PathParam("id") int bookingId) {
        boolean isDeleted = bookingSevice.deleteBooking(bookingId);
        if (isDeleted) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Booking Deleted successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to delete Booking\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}
