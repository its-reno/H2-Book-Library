package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection implements AutoCloseable{

    private static final String PROPERTIES_FILE = "src/main/resources/application.properties";
    private Connection connection;

    public DatabaseConnection() throws SQLException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
            String jdbcURL = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            this.connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    "borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "returned BOOLEAN DEFAULT FALSE," +
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
}
