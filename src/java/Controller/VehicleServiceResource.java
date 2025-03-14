/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Vehicle;
import Service.VehicleService;
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
        return Response.status(Response.Status.CREATED)
                       .entity("{\"message\": \"Vehicle added successfully\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
    return Response.status(Response.Status.BAD_REQUEST)
                   .entity("{\"error\": \"Failed to add Vehicle\"}") 
                   .type(MediaType.APPLICATION_JSON)
                   .build();
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
    
    @GET
    @Path("/availableCount")
    public Response getAvailableVehicles() {
        int availableVehicles = vehicleService.getAvailableVehicles();
        Map<String, Integer> response = new HashMap<>();
        response.put("availableVehicles", availableVehicles);
        return Response.ok(response).build();
    }
    
        
    @PUT
    @Path("updateVehicle/{vehicleId}")
    public Response updateVehicle(@PathParam("vehicleId") int vehicleId, Vehicle vehicle) {
        if (vehicle == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Vehicle data").build();
        }

        vehicle.setVehicleId(vehicleId);
        boolean isUpdated = vehicleService.updateVehicle(vehicleId , vehicle);

        if (isUpdated) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Vehicle Status Updated successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to update Vehicle Status\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
    
    @DELETE
    @Path("deleteVehicle/{id}")
    public Response deleteVehicleById(@PathParam("id") int vehicleId) {
        boolean isDeleted = vehicleService.deleteVehicleById(vehicleId);
        if (isDeleted) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Vehicle deleted successfully\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"Failed to deleted Vehicle\"}") 
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}

