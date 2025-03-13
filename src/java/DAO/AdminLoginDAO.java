/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Lahiru Sanjitha
 */
public class AdminLoginDAO {
    
    private final Connection connection;
    
    public AdminLoginDAO(){
        connection = DBUtil.getInstance().getConnection();
    }
    public int validateLogin(String userName, String password) {
        try {
            String query = "SELECT adminId,password FROM admin WHERE userName = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int adminId = rs.getInt("adminId");
                String hashedPassword = rs.getString("password"); 
                if (BCrypt.checkpw(password, hashedPassword)) {
                return adminId; 
               }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
