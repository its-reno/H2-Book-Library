package database;

import library.Book;
import java.sql.*;

public class BookService {

    private final Connection connection;

    public BookService(Connection connection) {
        this.connection = connection;
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

    //read data
    public void queryBookTable() {
        String selectSQL = "SELECT * FROM books";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            System.out.println("Reading data from 'books'");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int published_year = resultSet.getInt("published_year");
                String genre = resultSet.getString("genre");
                String isbn = resultSet.getString("isbn");
                boolean available = resultSet.getBoolean("available");
                System.out.printf("ID: %d, Title: %s, Author: %s, published year: %d, genre: %s, isbn: %s, is it available? %b%n", id, title, author, published_year, genre, isbn, available);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeBookFromDatabase(int bookID) {
        String removeSQL = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(removeSQL)) {
            statement.setInt(1, bookID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0)
                System.out.println("Book deleted.");
            else
                System.out.println("No book with that ID.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateBookAvailability(int bookID, boolean availability) {
        String updateBookSQL = "UPDATE books SET available = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateBookSQL)) {
            statement.setBoolean(1, availability);
            statement.setInt(2, bookID);
            int affectedRows = statement.executeUpdate();
            if(affectedRows > 0){
                System.out.println("Book availability updated.");
            } else {
                System.out.println("Book with ID: " + bookID + " doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fetchAvailableBooks() {
        System.out.println("Fetching all available books");
        String fetchSQL = "SELECT * FROM books WHERE available = true";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(fetchSQL)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int published_year = resultSet.getInt("published_year");
                String genre = resultSet.getString("genre");
                String isbn = resultSet.getString("isbn");
                boolean available = resultSet.getBoolean("available");
                System.out.printf("ID: %d, Title: %s, Author: %s, published year: %d, genre: %s, isbn: %s, is it available? %b%n", id, title, author, published_year, genre, isbn, available);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
