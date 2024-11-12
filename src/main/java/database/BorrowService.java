package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowService {

    private final Connection connection;

    public BorrowService(Connection connection) {
        this.connection = connection;
    }

    public void recordBorrow(int bookID, int userID) {
        String recordBorrowSQL = "INSERT INTO borrowed_books (bookID, userID) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(recordBorrowSQL)) {
            statement.setInt(1, bookID);
            statement.setInt(2, userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recordReturn(int bookID, int userID) {
        String recordReturnSQL = "UPDATE borrowed_books SET returned = TRUE WHERE bookID = ? AND userID = ?";
        try (PreparedStatement statement = connection.prepareStatement(recordReturnSQL)) {
            statement.setInt(1, bookID);
            statement.setInt(2, userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO:
    // fetchBorrowedBooks(int userId) - Retrieves books borrowed by a specific user.

}
