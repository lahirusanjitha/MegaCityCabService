/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BillDAO;
import Model.Bill;
import Service.BillService;
import java.io.FileNotFoundException;
import javax.ws.rs.Consumes;
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
    private final BillDAO billDAO = new BillDAO();
    
    @POST
    @Path("createBill/{bookingId}")
    public Response createBill(@PathParam("bookingId") int bookingId, Bill bill) {
        double totalFare = billService.calculateTotalFare(bill);

        boolean created = billService.createBill(bookingId, totalFare, bill);
        if (created) {
            return Response.status(Response.Status.CREATED).entity("Bill created successfully!").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Bill created failed!").build();
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
            return Response.ok("Bill PDF generated successfully at: " + filePath).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Bill generation failed or Booking ID not found.").build();
        }
    }

}
