package com.library.entity;
import java.time.LocalDate;

public class IssuedBookEntity {



        private int studentId;
        private int bookId;
        private LocalDate issueDate;
        private LocalDate returnDate;

//        public int getIssuedBookId() {
//            return issuedBookId;
//        }
//
//        public void setIssuedBookId(int issuedBookId) {
//            this.issuedBookId = issuedBookId;
//        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public LocalDate getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(LocalDate issueDate) {
            this.issueDate = issueDate;
        }

        public LocalDate getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
        }
    }

