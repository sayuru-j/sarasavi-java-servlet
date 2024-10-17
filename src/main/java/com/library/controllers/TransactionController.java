package com.library.controllers;

import com.library.dao.TransactionDAO;
import com.library.models.Transaction;
import com.library.services.TransactionService;
import com.library.utils.DatabaseConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TransactionController extends HttpServlet {
    private TransactionService transactionService;

    public TransactionController() {
        try {
            this.transactionService = new TransactionService(new TransactionDAO(DatabaseConnection.getConnection()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "return":
                    markAsReturned(request, response);
                    break;
                default:
                    listTransactions(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("insert".equals(action)) {
                insertTransaction(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listTransactions(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Transaction> transactions = transactionService.getAllTransactions();
        request.setAttribute("transactions", transactions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transaction-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("transaction-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertTransaction(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String borrowDate = request.getParameter("borrowDate");

        Transaction newTransaction = new Transaction(0, bookId, userId, java.sql.Date.valueOf(borrowDate), null);
        transactionService.addTransaction(newTransaction);
        response.sendRedirect("TransactionController");
    }

    private void markAsReturned(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        transactionService.updateTransaction(new Transaction(id, 0, 0, null, new java.sql.Date(System.currentTimeMillis())));
        response.sendRedirect("TransactionController");
    }
}
