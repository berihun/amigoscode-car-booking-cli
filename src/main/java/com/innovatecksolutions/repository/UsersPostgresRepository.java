package com.innovatecksolutions.repository;


import com.innovatecksolutions.DbConfig.DatabaseConnection;
import com.innovatecksolutions.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersPostgresRepository implements UsersRepository {

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT user_id, name FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setName(rs.getString("name"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Database error fetching users: " + e.getMessage());
        }

        return users;
    }

    @Override
    public void save(User user) { /* Implementation here */ }

    @Override
    public void update(User user) { /* Implementation here */ }

    @Override
    public void delete(User user) { /* Implementation here */ }
}