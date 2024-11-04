package org.example;


import java.sql.*;

public class App
{
    public static void main( String[] args )
    {


        String jdbcURL = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcURL, user, password)) {
            System.out.println("Connected to H2 database");

            //create a test table
            String createTableSL = "CREATE TABLE IF NOT EXISTS test (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "description VARCHAR(255)" +
                    ")";

            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableSL);
                System.out.println("Table 'test' created!");
            }

            //Insert data
            String insertSQL = "INSERT INTO test (title, description) VALUES(?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, "Test H2 DB");
                preparedStatement.setString(2, "Test2");
                preparedStatement.executeUpdate();
                System.out.println("Inserted data into the 'test' table");
            }

            //read data
            String selectSQL = "SELECT * FROM test";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSQL)) {
                System.out.println("Reading data from 'test'");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String descripton = resultSet.getString("description");
                    System.out.printf("ID: %d, Title: %s, Description: %s%n", id, title, descripton);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
