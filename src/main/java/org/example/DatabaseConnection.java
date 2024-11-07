package org.example;

import java.sql.*;

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

    public void createTables() {
        try (Statement statement = connection.createStatement()) {
            String createBooksTable = "CREATE TABLE IF NOT EXISTS books(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "title VARCHAR(255), " +
                    "author VARCHAR(255), " +
                    "published_year INT, " +
                    "genre VARCHAR(255), " +
                    "isbn VARCHAR(20), " +
                    "available BOOLEAN)";
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255))";
            String createBorrowedBooksTable = "CREATE TABLE IF NOT EXISTS borrowed_books(" +
                    "bookID INT, " +
                    "userID INT, " +
                    "FOREIGN KEY(bookID) REFERENCES books(id), " +
                    "FOREIGN KEY(userID) REFERENCES users(id))";
            statement.execute(createBooksTable);
            statement.execute(createUsersTable);
            statement.execute(createBorrowedBooksTable);
            System.out.println("Tables successfully created or they already exists!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void addBookToDatabase(Book book) {
        String query = "INSERT INTO books(title,author,published_year,genre,isbn,available) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPublished_year());
            statement.setString(4, book.getGenre());
            statement.setString(5, book.getIsbn());
            statement.setBoolean(6, book.getAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    TODO:
    removeBookFromDatabase(int bookId) - Delete a book record.
    updateBookAvailability(int bookId, boolean isAvailable) - Update the book’s availability status.
            addUserToDatabase(User user) - Add a user to the database.
    recordBorrow(int bookId, int userId) - Records a borrowing event.
    recordReturn(int bookId, int userId) - Records a return event.
            fetchAvailableBooks() - Retrieves all available books.
    fetchBorrowedBooks(int userId) - Retrieves books borrowed by a specific user.*/
}
