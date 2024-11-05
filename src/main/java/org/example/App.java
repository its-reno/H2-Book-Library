package org.example;


import java.sql.*;

public class App
{
    public static void main( String[] args )
    {
        try (DatabaseConnection databaseConnection = new DatabaseConnection();
             Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement();) {

            System.out.println("Connected to H2 database");

            //create a book table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS books (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "author VARCHAR(255) NOT NULL, " +
                    "published_year INT, " +
                    "genre VARCHAR(255), " +
                    "available BOOLEAN DEFAULT TRUE" +
                    ")";

                statement.execute(createTableSQL);
                System.out.println("Table 'books' created!");


            //Insert data
            String insertSQL = "INSERT INTO books (title, author, published_year, genre, available) VALUES(?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, "Harry Potter and the Philosopher's Stone");
                preparedStatement.setString(2, "J.K. Rowling");
                preparedStatement.setInt(3, 1997);
                preparedStatement.setString(4, "fantasy");
                preparedStatement.setBoolean(5, true);
                preparedStatement.executeUpdate();
                System.out.println("Inserted data into the 'books' table");
            }

            //read data
            String selectSQL = "SELECT * FROM books";
            try (ResultSet resultSet = statement.executeQuery(selectSQL)) {
                System.out.println("Reading data from 'books'");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    int published_year = resultSet.getInt("published_year");
                    String genre = resultSet.getString("genre");
                    boolean available = resultSet.getBoolean("available");
                    System.out.printf("ID: %d, Title: %s, Author: %s, published year: %d, genre: %s, is it available? %b%n", id, title, author, published_year, genre, available);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
