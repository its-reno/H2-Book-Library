package library;


import database.BookService;
import database.BorrowService;
import database.UserService;

import java.util.Scanner;

public class LibraryServices
{
    private final BookService bookService;
    private final UserService userService;
    private final BorrowService borrowService;
    private final Scanner scanner;

    public LibraryServices(BookService bookService, UserService userService, BorrowService borrowService, Scanner scanner) {
        this.bookService = bookService;
        this.userService = userService;
        this.borrowService = borrowService;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = getUserChoice();
            handleUserChoice(choice);
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
        System.out.println("6. Query users");
        System.out.println("7. Add new user");
        System.out.println("0. Quit program");
    }

    private int getUserChoice() {
        try {
            System.out.println("Enter your choice: ");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> bookService.queryBookTable();
            case 2 -> bookService.addBookToDatabase(addBook());
            case 3 -> removeBook();
            case 4 -> updateBookAvailability();
            case 5 -> bookService.fetchAvailableBooks();
            case 6 -> userService.fetchUsers();
            case 7 -> addUser();
            case 0 -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice, please choose a valid option.");
        }
    }

    //return a Book object based on user's input
    private Book addBook() {
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


    private void removeBook() {
        System.out.print("Enter the ID of the book you'd like to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        bookService.removeBookFromDatabase(id);
    }

    private void updateBookAvailability() {
        System.out.print("Enter the book ID to modify its availability: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new availability (a/available or u/unavailable): ");
        String availabilityInput = scanner.nextLine();

        AvailabilityStatus availability = availabilityInput.equalsIgnoreCase("a") ? AvailabilityStatus.AVAILABLE : AvailabilityStatus.UNAVAILABLE;
        bookService.updateBookAvailability(id, availability == AvailabilityStatus.AVAILABLE);
    }

    private void addUser () {
        System.out.print("Enter new user's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        userService.addUserToDatabase(new User(name, email));
    }
}
