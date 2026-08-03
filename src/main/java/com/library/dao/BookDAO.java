package com.library.dao;

import com.library.database.BookDatabase;


import com.library.database.DatabaseConnection;
import com.library.entity.BookEntity;

import java.sql.*;

public class BookDAO {

    // Add Book
    public void addBook(BookEntity book) {



            String insert = "INSERT INTO bookManagementSystem(bookName, bookAuthorName, bookPublishYear, bookQuantity, bookPrice) VALUES(?,?,?,?,?)";

            try {

                Connection connection = DatabaseConnection.getConnection();

                PreparedStatement ps = connection.prepareStatement(insert);

                ps.setString(1, book.getBookName());
                ps.setString(2, book.getBookAuthorName());
                ps.setInt(3, book.getBookPublishYear());
                ps.setInt(4, book.getBookQuantity());
                ps.setDouble(5, book.getBookPrice());

                int result = ps.executeUpdate();

                if (result > 0) {
                    System.out.println("Book Added Successfully");
                } else {
                    System.out.println("Book Not Added");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    // View All Books
    public void viewAllBooks() {

        String select = "SELECT * FROM bookManagementSystem";

        try {

            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(select);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("--------------------------------------");
                System.out.println("Book ID : " + rs.getInt("bookId"));
                System.out.println("Book Name : " + rs.getString("bookName"));
                System.out.println("Author : " + rs.getString("bookAuthorName"));
                System.out.println("Publish Year : " + rs.getInt("bookPublishYear"));
                System.out.println("Quantity : " + rs.getInt("bookQuantity"));
                System.out.println("Price : " + rs.getDouble("bookPrice"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchBook(int bookId) {

        String select = "SELECT * FROM bookManagementSystem WHERE bookId = ?";

        try {


            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(select);

            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Book ID : " + rs.getInt("bookId"));
                System.out.println("Book Name : " + rs.getString("bookName"));
                System.out.println("Author Name : " + rs.getString("bookAuthorName"));
                System.out.println("Publish Year : " + rs.getInt("bookPublishYear"));
                System.out.println("Quantity : " + rs.getInt("bookQuantity"));
                System.out.println("Price : " + rs.getDouble("bookPrice"));

            } else {

                System.out.println("Book Not Found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBook(BookEntity book) {

        String update = "UPDATE bookManagementSystem SET bookName=?, bookAuthorName=?, bookPublishYear=?, bookQuantity=?, bookPrice=? WHERE bookId=?";

        try {


            Connection connection =DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(update);

            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookAuthorName());
            ps.setInt(3, book.getBookPublishYear());
            ps.setInt(4, book.getBookQuantity());
            ps.setDouble(5, book.getBookPrice());
            ps.setInt(6, book.getBookId());

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Book Updated Successfully");
            } else {
                System.out.println("Book Not Found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteBook(int bookId) {

        String delete = "DELETE FROM bookManagementSystem WHERE bookId=?";

        try {


            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(delete);

            ps.setInt(1, bookId);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Book Deleted Successfully");
            } else {
                System.out.println("Book Not Found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isBookExists(int bookId) {

        String query = "SELECT * FROM bookmanagementsystem WHERE bookId = ?";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean isBookAvailable(int bookId) {

        String query = "SELECT bookQuantity FROM bookmanagementsystem WHERE bookId = ?";

        try {
            Connection con =  DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("bookQuantity") > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean decreaseBookQuantity(int bookId) {

        String query = "UPDATE bookmanagementsystem SET bookQuantity = bookQuantity - 1 WHERE bookId = ?";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean increaseBookQuantity(int bookId) {

        String query = "UPDATE bookmanagementsystem SET bookQuantity = bookQuantity + 1 WHERE bookId = ?";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 15. Available Books
    public void availableBooks() {

         String sql="SELECT * FROM bookManagementSystem WHERE bookQuantity > 0";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Book ID : " + rs.getInt("bookId"));
                System.out.println("Book Name : " + rs.getString("bookName"));
                System.out.println("Author : " + rs.getString("bookAuthorName"));
                System.out.println("Quantity : " + rs.getInt("bookQuantity"));
                System.out.println("Price : " + rs.getDouble("bookPrice"));
                System.out.println("--------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 16. Student Borrow History
    public void studentBorrowHistory() {

        String sql = "SELECT s.studentId, s.student_name, b.bookName, "
                + "bb.borrow_date, bb.due_date, bb.status "
                + "FROM borrow_book bb "
                + "JOIN students s ON bb.studentId = s.studentId "
                + "JOIN bookmanagementsystem b ON bb.bookId = b.bookId";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Student ID : " + rs.getInt("studentId"));
                System.out.println("Student Name : " + rs.getString("student_name"));
                System.out.println("Book Name : " + rs.getString("book_name"));
                System.out.println("Borrow Date : " + rs.getDate("borrow_date"));
                System.out.println("Due Date : " + rs.getDate("due_date"));
                System.out.println("Status : " + rs.getString("status"));
                System.out.println("--------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 17. Today's Due Books
    public void todayDueBooks() {

        String sql = "SELECT s.student_name, b.bookName, bb.due_date "
                + "FROM borrow_book bb "
                + "JOIN students s ON bb.studentId = s.studentId "
                + "JOIN bookmanagementsystem b ON bb.bookId = b.bookId "
                + "WHERE bb.due_date = CURDATE() "
                + "AND bb.status = 'Borrowed'";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Student Name : " + rs.getString("student_name"));
                System.out.println("Book Name : " + rs.getString("bookName"));
                System.out.println("Due Date : " + rs.getDate("due_date"));
                System.out.println("--------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 18. Overdue Books
    public void overDueBooks() {

        String sql = "SELECT s.student_name, b.bookName, bb.due_date "
                + "FROM borrow_book bb "
                + "JOIN students s ON bb.studentId = s.studentId "
                + "JOIN bookmanagementsystem b ON bb.bookId = b.bookId "
                + "WHERE bb.due_date < CURDATE() "
                + "AND bb.status = 'Borrowed'";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Student Name : " + rs.getString("student_name"));
                System.out.println("Book Name : " + rs.getString("bookName"));
                System.out.println("Due Date : " + rs.getDate("due_date"));
                System.out.println("--------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}