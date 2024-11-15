package library;

import database.BookService;
import database.BorrowService;
import database.DatabaseConnection;
import database.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             DatabaseConnection databaseConnection = new DatabaseConnection();
             Connection connection = databaseConnection.getConnection();

        ) {

            //Initializing services
            BookService bookService = new BookService(connection);
            UserService userService = new UserService(connection);
            BorrowService borrowService = new BorrowService(connection);

            //create tables if they didn't exist
            databaseConnection.createTables();
            System.out.println("Connected to H2 database");

            //start the application
            LibraryServices libraryApp = new LibraryServices(bookService, userService, borrowService, scanner);
            libraryApp.start();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
