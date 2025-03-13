/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Service.AdminLoginService;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Lahiru Sanjitha
 */
@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminLoginResource {
    
    private AdminLoginService adminService = new AdminLoginService();
    
    @POST
    @Path("login")
    public Response login(Map<String, String> credentials) {
        String userName = credentials.get("userName");
        String password = credentials.get("password");

        if (userName == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Username and password are required").build();
        }

        int adminId = adminService.login(userName, password); 

        if (adminId > 0) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("status", "success");
            response.put("adminId", adminId); 
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }
    }
}
