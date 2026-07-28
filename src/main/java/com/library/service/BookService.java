package com.library.service;

import com.library.dao.BookDAO;
import com.library.entity.BookEntity;

public class BookService {

    BookDAO dao = new BookDAO();

    public void addBook(BookEntity book) {
        dao.addBook(book);
    }

    public void viewAllBooks() {
        dao.viewAllBooks();
    }

    public void searchBook(int bookId) {
        dao.searchBook(bookId);
    }

    public void updateBook(BookEntity book) {
        dao.updateBook(book);
    }

    public void deleteBook(int bookId) {
        dao.deleteBook(bookId);
    }
}