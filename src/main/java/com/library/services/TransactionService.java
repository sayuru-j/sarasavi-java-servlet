package com.library.services;

import com.library.dao.TransactionDAO;
import com.library.models.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TransactionService {
    private TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        return transactionDAO.getAllTransactions();
    }

    public void addTransaction(Transaction transaction) throws SQLException {
        transactionDAO.addTransaction(transaction);
    }

    public void updateTransaction(Transaction transaction) throws SQLException {
        transactionDAO.updateTransaction(transaction);
    }
}
