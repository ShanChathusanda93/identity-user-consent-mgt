package org.wso2.identity.carbon.user.consent.mgt.backend.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    String URL = "jdbc:mysql://localhost:3306/user_consent?useSSL=false";
    String USER = "root";
    String PASSWORD = "ShanWso293";
    String DRIVER = "com.mysql.jdbc.Driver";
    Connection connection;

    public boolean connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return true;
        } catch (Exception e) {
            System.out.println("Error :" + e);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
