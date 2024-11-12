package database;

import library.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    private final Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
    }

    public void addUserToDatabase(User user) {
        String addUserSQL = "INSERT INTO users (name, email) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(addUserSQL)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
