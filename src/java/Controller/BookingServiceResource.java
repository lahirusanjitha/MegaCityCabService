/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Booking;
import Service.BookingService;
import java.util.List;
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
            return Response.status(Response.Status.CREATED).entity("Booking successfully created").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Failed to create booking").build();
        }
    }
    @GET
    @Path("/getAllBookings")
    public Response getAllBookings() {
        List<Booking> bookings = bookingSevice.getAllBookings();
        return Response.ok(bookings).build();
    }
    @GET
    @Path("/{bookingId}")
    public Response getBookingById(@PathParam("bookingId") int bookingId) {
        Booking booking = bookingSevice.getBookingById(bookingId);
        if (booking != null) {
            return Response.ok(booking).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Booking not found").build();
        }
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
            return Response.ok("Booking updated successfully").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Failed to update booking").build();
        }
    }

    @PUT
    @Path("cancelBooking/{bookingId}")
    public Response cancelBooking(@PathParam("bookingId") int bookingId) {
        boolean isCanceled = bookingSevice.cancelBooking(bookingId);
        if (isCanceled) {
            return Response.ok("Booking canceled successfully").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Failed to cancel booking").build();
        }
    }
    
    @DELETE
    @Path("deleteBooking/{id}")
    public Response deleteBooking(@PathParam("id") int bookingId) {
        boolean isDeleted = bookingSevice.deleteBooking(bookingId);
        if (isDeleted) {
            return Response.ok("Deleted booking Successdully").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("cannot Delete Booking not found").build();
    }
}
