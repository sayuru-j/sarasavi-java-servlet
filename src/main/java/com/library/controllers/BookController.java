package com.library.controllers;

import com.library.dao.BookDAO;
import com.library.models.Book;
import com.library.services.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookController extends HttpServlet {
    private BookService bookService;

    public BookController() {
        this.bookService = new BookService(new BookDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.equals("list")) {
                listBooks(request, response);
            } else if (action.equals("edit")) {
                showEditForm(request, response);
            } else if (action.equals("delete")) {
                deleteBook(request, response);
            } else if (action.equals("new")) {
                showNewForm(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action.equals("insert")) {
                insertBook(request, response);
            } else if (action.equals("update")) {
                updateBook(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Book> books = bookService.getAllBooks();
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/book-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookService.getBookById(id);
        request.setAttribute("book", existingBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/book-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));

        Book newBook = new Book(0, title, author, category, isAvailable);
        //System.out.println(newBook);
        bookService.addBook(newBook);
//        // Redirecting to /books?action=list after insertion
//        response.sendRedirect(request.getContextPath() + "/books?action=list");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));

        Book updatedBook = new Book(id, title, author, category, isAvailable);
        System.out.println("updatedBook: " + updatedBook);
        System.out.println("IsAvailable: " + isAvailable);
        bookService.updateBook(updatedBook);
        response.sendRedirect(request.getContextPath() + "/books?action=list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookService.deleteBook(id);
        // Redirecting to /books?action=list after deletion
        response.sendRedirect(request.getContextPath() + "/books?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/book-form.jsp");
        dispatcher.forward(request, response);
    }
}
