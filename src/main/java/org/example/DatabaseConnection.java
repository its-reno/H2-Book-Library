package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable{

    private static final String JDBC_URL = "jdbc:h2:./data/testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private Connection connection;

    public DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() throws Exception {
        try {
            if(this.connection != null && !this.connection.isClosed()){
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
