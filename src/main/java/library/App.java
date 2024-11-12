package library;


import database.BookService;
import database.DatabaseConnection;

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
             BookService bookService = new BookService(connection);

            //create the tables
            databaseConnection.createTables();
            System.out.println("Connected to H2 database");

                while (true) {
                showMenu();
                int choice = getUserChoice(scanner);
                switch(choice) {
                    case 1: //query the book table
                        bookService.queryBookTable();
                        break;
                    case 2: //add new book
                        bookService.addBookToDatabase(addBook(scanner));
                        break;
                    case 3: //delete book
                        System.out.println("Enter the ID of the book you'd like to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        bookService.removeBookFromDatabase(id);
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
                        bookService.updateBookAvailability(id, isAvailable);
                        break;
                    case 5:
                        System.out.println("Fetching all available books");
                        bookService.fetchAvailableBooks();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Wrong choice, choose between 0-3.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void showMenu() {
        System.out.println("=================================");
        System.out.println("Select what do you want to do: ");
        System.out.println("1. Query books database");
        System.out.println("2. Add new book to the database");
        System.out.println("3. Remove book from the database");
        System.out.println("4. Change availability of a book");
        System.out.println("5. List available books");
        System.out.println("0. Quit program");
    }

    private static int getUserChoice(Scanner scanner) {
        try {
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
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
