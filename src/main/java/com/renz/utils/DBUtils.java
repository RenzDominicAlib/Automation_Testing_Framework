package com.renz.utils;

import com.renz.helpers.PropertyReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    // ThreadLocal ensures each parallel thread has its own isolated DB connection
    private static ThreadLocal<Connection> dbConnection = new ThreadLocal<>();

    public static Connection getConnection() {
        if (dbConnection.get() == null) {
            try {
                // PropertyReader now knows which env (dev/qa) to pull from
                dbConnection.set(DriverManager.getConnection(
                        PropertyReader.getProp("db.url"),
                        PropertyReader.getProp("db.username"),
                        PropertyReader.getProp("db.password")
                ));
            } catch (SQLException e) {
                throw new RuntimeException("DB Connection Failed!", e);
            }
        }
        return dbConnection.get();
    }

    public static void closeConnection() {
        if (dbConnection.get() != null) {
            try {
                dbConnection.get().close(); // Close the physical connection
                dbConnection.remove();      // Clear the ThreadLocal memory
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
