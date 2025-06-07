package com.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn;

    public static Connection getConn() {
        try {
            Class.forName("org.postgresql.Driver"); // must load driver
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital", "postgres", "root");
        } catch (Exception e) {
            System.out.println("❌ Database connection failed:");
            e.printStackTrace();
        }
        return conn;
    }
}
