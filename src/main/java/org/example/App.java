package org.example;


import java.sql.*;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        try (DatabaseConnection databaseConnection = new DatabaseConnection();
             Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            System.out.println("Connected to H2 database");


            //create the tables
            databaseConnection.createTables();


            //Insert data
            //TODO: refactor code, move to new method in new class
            //TODO: input validation
            //TODO: only insert if ISBN is unique
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the details of the new book:");
            System.out.println("Title: ");
            String title = scanner.nextLine();
            System.out.println("Author: ");
            String author = scanner.nextLine();
            System.out.println("Published year: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Genre: ");
            String genre = scanner.nextLine();
            System.out.println("ISBN: ");
            String isbn = scanner.nextLine();
            Book book = new Book(title, author, year, genre, isbn);
            databaseConnection.addBookToDatabase(book);


            //read data
            String selectSQL = "SELECT * FROM books";
            try (ResultSet resultSet = statement.executeQuery(selectSQL)) {
                System.out.println("Reading data from 'books'");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    title = resultSet.getString("title");
                    author = resultSet.getString("author");
                    int published_year = resultSet.getInt("published_year");
                    genre = resultSet.getString("genre");
                    isbn = resultSet.getString("isbn");
                    boolean available = resultSet.getBoolean("available");
                    System.out.printf("ID: %d, Title: %s, Author: %s, published year: %d, genre: %s, isbn: %s, is it available? %b%n", id, title, author, published_year, genre, isbn, available);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
