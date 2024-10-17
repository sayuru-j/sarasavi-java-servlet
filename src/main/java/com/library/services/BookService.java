package com.library.services;

import com.library.dao.BookDAO;
import com.library.models.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(int id) throws SQLException {
        return bookDAO.getBookById(id);
    }

    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) throws SQLException {
        bookDAO.updateBook(book);
    }

    public void deleteBook(int id) throws SQLException {
        bookDAO.deleteBook(id);
    }
}
