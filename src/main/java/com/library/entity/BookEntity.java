package com.library.entity;

public class BookEntity {


        private int bookId;
        private String bookName;
        private String bookAuthorName;
        private int bookPublishYear;
        private int bookQuantity;
        private double bookPrice;

        // Default Constructor
        public BookEntity() {

        }

        // Parameterized Constructor
        public BookEntity(int bookId, String bookName, String bookAuthorName,
                    int bookPublishYear, int bookQuantity, double bookPrice) {

            this.bookId = bookId;
            this.bookName = bookName;
            this.bookAuthorName = bookAuthorName;
            this.bookPublishYear = bookPublishYear;
            this.bookQuantity = bookQuantity;
            this.bookPrice = bookPrice;
        }

        // Getter Methods
        public int getBookId() {
            return bookId;
        }

        public String getBookName() {
            return bookName;
        }

        public String getBookAuthorName() {
            return bookAuthorName;
        }

        public int getBookPublishYear() {
            return bookPublishYear;
        }

        public int getBookQuantity() {
            return bookQuantity;
        }

        public double getBookPrice() {
            return bookPrice;
        }

        // Setter Methods
        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public void setBookAuthorName(String bookAuthorName) {
            this.bookAuthorName = bookAuthorName;
        }

        public void setBookPublishYear(int bookPublishYear) {
            this.bookPublishYear = bookPublishYear;
        }

        public void setBookQuantity(int bookQuantity) {
            this.bookQuantity = bookQuantity;
        }

        public void setBookPrice(double bookPrice) {
            this.bookPrice = bookPrice;
        }

        @Override
        public String toString() {
            return "Book [bookId=" + bookId +
                    ", bookName=" + bookName +
                    ", bookAuthorName=" + bookAuthorName +
                    ", bookPublishYear=" + bookPublishYear +
                    ", bookQuantity=" + bookQuantity +
                    ", bookPrice=" + bookPrice + "]";
        }
    }

