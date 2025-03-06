/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Vehicle;
import Service.VehicleService;
import java.util.List;
import javax.ws.rs.Consumes;
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
@Path("/vehicles")  
@Produces(MediaType.APPLICATION_JSON)  
@Consumes(MediaType.APPLICATION_JSON) 
   
public class VehicleServiceResource {
        private final VehicleService vehicleService = new VehicleService();
        
    @POST
    @Path("/addVehicle")
    public Response addVehicle(Vehicle vehicle) {
        boolean success = vehicleService.addVehicle(vehicle);
        if (success) {
            return Response.status(Response.Status.CREATED).entity("Vehicle Added successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Failed to add Vehicle").build();
    }
        
    @GET
    @Path("/getAllVehicles")
    public Response getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return Response.ok(vehicles).build();
    }
   
    @GET
    @Path("/{id}")
    public Response getVehicleById(@PathParam("id") int vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle != null) {
            return Response.ok(vehicle).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("vehicle not found").build();
    }
    
        
    @PUT
    @Path("/{id}/status")
    public Response updateVehicleStatus(@PathParam("id") int vehicleId, @QueryParam("status") String status) {
        boolean updated = vehicleService.updateVehicleStatus(vehicleId, status);
        if (updated) {
            return Response.ok("Vehicle status updated successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Failed to update vehicle status").build();
    }
    
    @GET
    @Path("deleteVehicle/{id}")
    public Response deleteVehicleById(@PathParam("id") int vehicleId) {
        boolean isDeleted = vehicleService.deleteVehicleById(vehicleId);
        if (isDeleted) {
            return Response.ok("Deleted Vehicle Successdully").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("cannot Delete Vehicle not found").build();
    }
}

