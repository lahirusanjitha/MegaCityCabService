/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lahiru Sanjitha
 */
public class DBUtil {
    
        private static DBUtil instance;
    private Connection connection;
    
    private static final String URL = "jdbc:mysql://localhost:3306/megacitycab";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
        private DBUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
        public static DBUtil getInstance() {
        if (instance == null) {
            instance = new DBUtil();
        }
        return instance;
    }
        public Connection getConnection() {
        return connection;
    }
        public void closeConnection() {
         try {
            if (connection != null) {
                connection.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
