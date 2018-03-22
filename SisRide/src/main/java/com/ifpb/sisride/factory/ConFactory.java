package com.ifpb.sisride.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {

    private static final String url = "jdbc:postgresql://127.0.0.1:5432/SisRide";
    private static final String user = "postgres";
    private static final String password = "flavio22";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
