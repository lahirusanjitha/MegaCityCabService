/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.CustomerDAO;
import Model.Customer;
import java.util.List;

/**
 *
 * @author Lahiru Sanjitha
 */
public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();
    
    public boolean register(Customer customer) {
        return customerDAO.registerCustomer(customer);
    }
    public boolean login(String userName, String password) {
        return customerDAO.validateLogin(userName, password);
    }
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }
    public boolean updateCustomer(int customerId, Customer customer) {
        return customerDAO.updateCustomer(customerId, customer);
    }
    public boolean deleteCustomerById(int customerId) {
        return customerDAO.deleteCustomerById(customerId);
    }


 }
