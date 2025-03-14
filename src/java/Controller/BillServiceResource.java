/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bill;
import Service.BillService;
import java.io.FileNotFoundException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Lahiru Sanjitha
 */
@Path("/bills")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillServiceResource {
    private final BillService billService = new BillService();
    
    @POST
    @Path("createBill/{bookingId}")
    public Response createBill(@PathParam("bookingId") int bookingId, Bill bill) {
        double totalFare = billService.calculateTotalFare(bill);

        boolean created = billService.createBill(bookingId, totalFare, bill);
        if (created) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Bill Created successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to Create Bill\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
    
    @GET
    @Path("/getAllBills")
    public Response getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return Response.ok(bills).build();
    }
    
    @GET
    @Path("getBillByBookingId/{bookingId}")
    public Response getBillByBookingId(@PathParam("bookingId") int bookingId) {
        Bill bill = billService.getBillByBookingId(bookingId);
        if (bill != null) {
            return Response.ok(bill).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Bill not found!").build();
    }
    
    @GET
    @Path("/printpdf/{bookingId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response generateBillPdf(@PathParam("bookingId") int bookingId) throws FileNotFoundException {
        String filePath = billService.generateBillPdf(bookingId);

        if (filePath != null) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Bill Printed successfully See Downloads \"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to Print Bill\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
    
    
    @DELETE
    @Path("deleteBill/{id}")
    public Response deleteBill(@PathParam("id") int billId) {
        boolean isDeleted = billService.deleteBillById(billId);
        if (isDeleted) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Bill Deleted successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to Delete Bill\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }

}
