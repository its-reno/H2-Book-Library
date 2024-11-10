package org.example;


import java.sql.*;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        try (Scanner scanner = new Scanner(System.in);
             DatabaseConnection databaseConnection = new DatabaseConnection();
             Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            //create the tables
            databaseConnection.createTables();
            System.out.println("Connected to H2 database");

            while (true) {


            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
                case 1: //query the book table
                    databaseConnection.queryBookTable();
                    break;
                case 2: //add new book
                    Book book = addBook(scanner);
                    databaseConnection.addBookToDatabase(book);
                    break;
                case 3: //delete book
                    System.out.println("Enter the ID of the book you'd like to delete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    databaseConnection.removeBookFromDatabase(id);
                    break;
                case 4:
                    System.out.println("Enter the book ID to modify its availability:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the new availability of the book. Enter a/available, or u/unavailable.");
                    String available = scanner.nextLine();
                    boolean isAvailable;
                    if(available.equals("a") || available.equals("available")){
                        isAvailable = true;
                    } else {
                        isAvailable = false;
                    }
                    databaseConnection.updateBookAvailability(id, isAvailable);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Wrong choise, choose between 0-3.");
                    break;

            }}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void showMenu() {
        System.out.println("=================================");
        System.out.println("Select what do you want to do: ");
        System.out.println("1. Query books database");
        System.out.println("2. Add new book to the database");
        System.out.println("3. Remove book from the database");
        System.out.println("4. Change availability of a book");
        System.out.println("0. Quit program");
    }

    //return a Book object based on user's input
    private static Book addBook(Scanner scanner) {
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

        return new Book(title, author, year, genre, isbn);
    }
}
