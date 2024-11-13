package database;

import java.sql.*;

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
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recordReturn(int bookID, int userID) {
        String recordReturnSQL = "UPDATE borrowed_books SET returned = TRUE WHERE bookID = ? AND userID = ?";
        try (PreparedStatement statement = connection.prepareStatement(recordReturnSQL)) {
            statement.setInt(1, bookID);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public void fetchBorrowedBooks(int userID) {
        String fetchSQL = "SELECT * FROM borrowed_books WHERE userID = ? AND returned = FALSE";
       try (PreparedStatement statement = connection.prepareStatement(fetchSQL)) {
           statement.setInt(1, userID);

           try (ResultSet resultSet = statement.executeQuery()) {
               while (resultSet.next()) {
                   int bookID = resultSet.getInt("bookID");
                   int user_ID = resultSet.getInt("userID");
                   Timestamp borrowDate = resultSet.getTimestamp("borrowed_date");
                   boolean returned = resultSet.getBoolean("returned");

                   System.out.printf("Book ID: %d, User ID: %d, Borrow Date: %s, returned: %s%n", bookID, user_ID, borrowDate, returned);
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

}
