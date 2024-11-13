package database;

import library.User;

import java.sql.*;

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
            statement.executeUpdate();
            System.out.println("User added succesfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fetchUsers() {
        String selectUsersSQL = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectUsersSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.printf("User ID: %d, Name: %s, Email: %s%n", id, name, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
