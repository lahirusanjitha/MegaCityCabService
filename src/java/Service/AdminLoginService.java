/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.AdminLoginDAO;

/**
 *
 * @author Lahiru Sanjitha
 */
public class AdminLoginService {
     private AdminLoginDAO adminDAO = new AdminLoginDAO();
     
     public int login(String userName, String password) {
        return adminDAO.validateLogin(userName, password);
    }
}
