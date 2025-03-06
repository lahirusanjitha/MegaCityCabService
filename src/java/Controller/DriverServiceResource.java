/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Driver;
import Service.DriverService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Lahiru Sanjitha
 */
@Path("/drivers")  
@Produces(MediaType.APPLICATION_JSON)  
@Consumes(MediaType.APPLICATION_JSON) 
public class DriverServiceResource {
    private DriverService driverService = new DriverService();
    
   
    @POST
    @Path("/addDriver")
    public Response addDriver(Driver driver) {
        boolean success = driverService.addDriver(driver);
        if (success) {
            return Response.status(Response.Status.CREATED).entity("Driver added successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Failed to add driver").build();
    }
    
    @GET
    @Path("/getAllDrivers")
    public Response getAllDrivers() {

        List<Driver> drivers = driverService.getAllDrivers();
        return Response.ok(drivers).build();
    }
    @GET
    @Path("/{id}")
    public Response getDriverById(@PathParam("id") int driverId) {
        Driver driver = driverService.getDriverById(driverId);
        if (driver != null) {
            return Response.ok(driver).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Driver not found").build();
    }
    
    @PUT
    @Path("/{id}/status")
    public Response updateDriverStatus(@PathParam("id") int driverId, @QueryParam("status") String status) {
        boolean updated = driverService.updateDriverStatus(driverId, status);
        if (updated) {
            return Response.ok("Driver status updated successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Failed to update driver status").build();
    }
    
    @DELETE
    @Path("deleteDriver/{id}")
    public Response deleteDriveById(@PathParam("id") int driverId) {
        boolean isDeleted = driverService.deleteDriverById(driverId);
        if (isDeleted) {
            return Response.ok("Deleted Driver Successdully").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("cannot Delete Driver not found").build();
    }
}
