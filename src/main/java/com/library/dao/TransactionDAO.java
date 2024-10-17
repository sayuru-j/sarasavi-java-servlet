package com.library.dao;

import com.library.models.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private Connection connection;

    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";

        try (Connection conn = connection;
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getInt("id"),
                        rs.getInt("bookId"),
                        rs.getInt("userId"),
                        rs.getDate("borrowDate"),
                        rs.getDate("returnDate")
                );
                transactions.add(transaction);
            }
        }

        return transactions;
    }

    public void addTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions (bookId, userId, borrowDate, returnDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = connection;
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transaction.getBookId());
            stmt.setInt(2, transaction.getUserId());
            stmt.setDate(3, new Date(transaction.getBorrowDate().getTime()));
            stmt.setDate(4, new Date(transaction.getReturnDate().getTime()));
            stmt.executeUpdate();
        }
    }

    public void updateTransaction(Transaction transaction) throws SQLException {
        String query = "UPDATE transactions SET returnDate = ? WHERE id = ?";

        try (Connection conn = connection;
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, new Date(transaction.getReturnDate().getTime()));
            stmt.setInt(2, transaction.getId());
            stmt.executeUpdate();
        }
    }
}
