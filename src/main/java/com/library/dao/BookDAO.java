package com.library.dao;

import com.library.database.BookDatabase;


import com.library.entity.BookEntity;

import java.sql.*;

public class BookDAO {

    // Add Book
    public void addBook(BookEntity book) {



            String insert = "INSERT INTO bookManagementSystem(bookName, bookAuthorName, bookPublishYear, bookQuantity, bookPrice) VALUES(?,?,?,?,?)";

            try {

                Connection connection = BookDatabase.getConnection();

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

            Connection connection = BookDatabase.getConnection();

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


            Connection connection = BookDatabase.getConnection();
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


            Connection connection = BookDatabase.getConnection();
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


            Connection connection = BookDatabase.getConnection();

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
}