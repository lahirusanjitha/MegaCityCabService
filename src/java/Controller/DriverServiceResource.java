/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Driver;
import Service.DriverService;
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
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Driver added successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to add Driver\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
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
    
    @GET
    @Path("/totalDrivers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegisteredDrivers() {
        int registeredDrivers = driverService.getRegisteredDrivers();
        Map<String, Integer> response = new HashMap<>();
        response.put("registeredDrivers", registeredDrivers);
        return Response.ok(response).build();
    }
    
    @PUT
    @Path("updateDriver/{driverId}")
    public Response updateDriver(@PathParam("driverId") int driverId, Driver driver) {
        if (driver == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid booking data").build();
        }

        driver.setDriverId(driverId);
        boolean isUpdated = driverService.updateDriverStatus(driverId , driver);

       if (isUpdated) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Driver updated successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to update Driver\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
    
    @DELETE
    @Path("deleteDriver/{id}")
    public Response deleteDriveById(@PathParam("id") int driverId) {
        boolean isDeleted = driverService.deleteDriverById(driverId);
        if (isDeleted) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Driver Deleted successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to Delete Driver\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
  
}
