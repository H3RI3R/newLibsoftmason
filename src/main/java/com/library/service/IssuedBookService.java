package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.IssuedBookDao;
import com.library.dao.StudentDAO;
import com.library.entity.IssuedBookEntity;

public class IssuedBookService {

    IssuedBookDao issuedDao = new IssuedBookDao();
    BookDAO bookDao = new BookDAO();
    StudentDAO studentDao = new StudentDAO();

    // Issue Book
    public void issueBook(IssuedBookEntity issueBook) {

        // Check Student
        if (!studentDao.isStudentExists(issueBook.getStudentId())) {
            System.out.println("Student ID Not Found.");
            return;
        }

        // Check Book
        if (!bookDao.isBookExists(issueBook.getBookId())) {
            System.out.println("Book ID Not Found.");
            return;
        }
        // Check Student Borrow Limit
        if (issuedDao.getIssuedBookCount(issueBook.getStudentId()) >= 3) {

            System.out.println("=================================");
            System.out.println("Student already borrowed 4 books.");
            System.out.println("Maximum borrowing limit is 4 books.");
            System.out.println("Please return a book before issuing a new one.");
            System.out.println("=================================");

            return;
        }

        // Check Book Quantity
        if (!bookDao.isBookAvailable(issueBook.getBookId())) {
            System.out.println("Book is Out Of Stock.");
            return;
        }

        // Issue Book
        boolean status = issuedDao.issueBook(issueBook);

        if (status) {

            // Decrease Quantity
            bookDao.decreaseBookQuantity(issueBook.getBookId());

            System.out.println("Book Issued Successfully.");
            System.out.println("Issue Date : " + issueBook.getIssueDate());
            System.out.println("Return Date : " + issueBook.getReturnDate());

        } else {
            System.out.println("Book Issue Failed.");
        }
    }

    // View All Issued Books
    public void viewAllIssuedBooks() {
        issuedDao.viewAllIssuedBooks();
    }

    // Search Issued Book
    public void searchIssuedBook(int issuedBookId) {
        issuedDao.searchIssuedBook(issuedBookId);
    }

    // Return Book
    public void returnBook(int issuedBookId) {

        int bookId = issuedDao.getBookIdByIssuedBookId(issuedBookId);

        if (bookId == -1) {
            System.out.println("Issued Book Not Found.");
            return;
        }

        // Increase Quantity
        bookDao.increaseBookQuantity(bookId);

        // Delete Issued Record
        boolean status = issuedDao.returnBook(issuedBookId);

        if (status) {
            System.out.println("Book Returned Successfully.");
        } else {
            System.out.println("Book Return Failed.");
        }
    }
    public void availableBooks() {
        issuedDao.availableBooks();
    }

    public void studentBorrowHistory() {
        issuedDao.studentBorrowHistory();
    }
    public void todayDueBooks() {
        issuedDao.todayDueBooks();
    }
    public void overDueBooks() {
        issuedDao.overDueBooks();
    }


}