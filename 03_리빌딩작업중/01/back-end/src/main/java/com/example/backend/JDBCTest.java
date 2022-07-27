package com.example.backend;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {

    public static void main(String[] args) {
        String driver = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
        String url = "jdbc:log4jdbc:oracle:thin:@127.0.0.1:1521/orcl";
        String user = "gumin";
        String password = "1234";
        /*String user = "gumin";
        String password = "Mingu1234567890";*/
        try {
            Class.forName(driver);
            System.out.println("jdbc driver success");
            DriverManager.getConnection(url, user, password);
            System.out.println("Oracle Done");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver failure");
        } catch (SQLException e) {
            System.out.println("Oracle Failure");
        }
    }
}
