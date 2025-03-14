/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Customer;
import Service.CustomerService;
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
@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CustomerServiceResource {
    private CustomerService customerService = new CustomerService();
    
    @POST
    @Path("/register")
    public Response register(Customer customer) {
        if (customerService.register(customer)) {
            return Response.ok().entity("Customer registered successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Customer registration failed").build();
    }
    
    @POST
    @Path("login")
    public Response login(Map<String, String> credentials) {
        String userName = credentials.get("userName");
        String password = credentials.get("password");

        if (userName == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Username and password are required").build();
        }

        int customerId = customerService.login(userName, password); 

        if (customerId > 0) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("status", "success");
            response.put("customerId", customerId); 
            return Response.ok(response).build();
        } else {
                       return Response.status(Response.Status.CREATED)
                           .entity("{\"error\": \"Invaild cardinatials\"}") 
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
    }
    @GET
    @Path("/getAllCustomers")
    public Response getAllCustomers() {

        List<Customer> customers = customerService.getAllCustomers();
        return Response.ok(customers).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return Response.ok(customer).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("customer not found").build();
    }
    @GET
    @Path("/totalCustomers")
    public Response getRegisteredCustomers() {
        int registeredCustomers = customerService.getRegisteredCustomers();
        Map<String, Integer> response = new HashMap<>();
        response.put("registeredCustomers", registeredCustomers);
        return Response.ok(response).build();
    }
         
    @PUT
    @Path("updateCustomer/{customerId}")
    public Response updateCustomer(@PathParam("customerId") int customerId, Customer customer) {
        if (customer == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid customer data").build();
        }

        customer.setId(customerId);
        boolean isUpdated = customerService.updateCustomer(customerId , customer);

        if (isUpdated) {
            return Response.ok("Customer updated successfully").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Failed to update customer").build();
        }
    }
    
    @DELETE
    @Path("deleteCustomer/{id}")
    public Response deleteCustomerById(@PathParam("id") int customerId) {
        boolean isDeleted = customerService.deleteCustomerById(customerId);
        if (isDeleted) {
            return Response.ok("Deleted Customer Successdully").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("cannot Delete Customer not found").build();
    }
}
    
