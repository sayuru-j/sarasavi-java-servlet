package com.library.dao;

import com.library.models.User;
import com.library.models.Student;
import com.library.models.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection conn = connection;
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                if (rs.getString("role").equals("Student")) {
                    users.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("course")));
                } else if (rs.getString("role").equals("Staff")) {
                    users.add(new Staff(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("department")));
                }
            }
        }

        return users;
    }

    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = connection;
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    if (rs.getString("role").equals("Student")) {
                        return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("course"));
                    } else if (rs.getString("role").equals("Staff")) {
                        return new Staff(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("department"));
                    }
                }
            }
        }

        return null;
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (name, email, role, course_or_department) VALUES (?, ?, ?, ?)";

        try (Connection conn = connection;
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole());

            if (user instanceof Student) {
                stmt.setString(4, ((Student) user).getCourse());
            } else if (user instanceof Staff) {
                stmt.setString(4, ((Staff) user).getDepartment());
            }

            stmt.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET name = ?, email = ?, role = ?, course_or_department = ? WHERE id = ?";

        try (Connection conn = connection;
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole());

            if (user instanceof Student) {
                stmt.setString(4, ((Student) user).getCourse());
            } else if (user instanceof Staff) {
                stmt.setString(4, ((Staff) user).getDepartment());
            }

            stmt.setInt(5, user.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection conn = connection;
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
