package com.library.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.library.database.DatabaseConnection;
import com.library.entity.IssuedBookEntity;

public class IssuedBookDao {
    // Issue a Book
    public boolean issueBook(IssuedBookEntity book) {

        String query = "INSERT INTO issued_books(studentId, bookId, issueDate, returnDate) VALUES(?,?,?,?)";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, book.getStudentId());
            ps.setInt(2, book.getBookId());
            ps.setDate(3, java.sql.Date.valueOf(book.getIssueDate()));
            ps.setDate(4, java.sql.Date.valueOf(book.getReturnDate()));

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // View All Issued Books
    public void viewAllIssuedBooks() {

        String query = "SELECT * FROM issued_books";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("----------------------------");
                System.out.println("Issued Book ID : " + rs.getInt("issuedBookId"));
                System.out.println("Student ID     : " + rs.getInt("studentId"));
                System.out.println("Book ID        : " + rs.getInt("bookId"));
                System.out.println("Issue Date     : " + rs.getDate("issueDate"));
                System.out.println("Return Date    : " + rs.getDate("returnDate"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search Issued Book
    public void searchIssuedBook(int issuedBookId) {

        String query = "SELECT * FROM issued_books WHERE issuedBookId = ?";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, issuedBookId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("----------------------------");
                System.out.println("Issued Book ID : " + rs.getInt("issuedBookId"));
                System.out.println("Student ID     : " + rs.getInt("studentId"));
                System.out.println("Book ID        : " + rs.getInt("bookId"));
                System.out.println("Issue Date     : " + rs.getDate("issueDate"));
                System.out.println("Return Date    : " + rs.getDate("returnDate"));
            } else {

                System.out.println("Issued Book Not Found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return Book
    public boolean returnBook(int issuedBookId) {

        String query = "DELETE FROM issued_books WHERE issuedBookId = ?";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, issuedBookId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getBookIdByIssuedBookId(int issuedBookId) {

        String query = "SELECT bookId FROM issued_books WHERE issuedBookId = ?";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, issuedBookId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("bookId");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    public void availableBooks() {

        String sql = "SELECT * FROM bookManagementSystem WHERE bookQuantity > 0";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("--------------------------------");
                System.out.println("Book ID : " + rs.getInt("bookId"));
                System.out.println("Book Name : " + rs.getString("bookName"));
                System.out.println("Author : " + rs.getString("bookAuthorName"));
                System.out.println("Quantity : " + rs.getInt("bookQuantity"));
                System.out.println("Price : " + rs.getDouble("bookPrice"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void studentBorrowHistory() {

        String sql = "SELECT s.studentId,s.student_name,b.bookName,"
                + "i.issueDate,i.returnDate "
                + "FROM issued_books i "
                + "JOIN students s ON i.studentId=s.studentId "
                + "JOIN bookManagementSystem b ON i.bookId=b.bookId";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("--------------------------------");
                System.out.println("Student ID : " + rs.getInt("studentId"));
                System.out.println("Student Name : " + rs.getString("student_name"));
                System.out.println("Book Name : " + rs.getString("bookName"));
                System.out.println("Issue Date : " + rs.getDate("issueDate"));
                System.out.println("Return Date : " + rs.getDate("returnDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

