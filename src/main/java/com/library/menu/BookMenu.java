package com.library.menu;

import java.util.Scanner;

import com.library.entity.BookEntity;
import com.library.service.BookService;
import com.library.service.StudentService;

public class BookMenu {
public static void validYear(int year){
    if(year < 1000 || year>9999){
        System.out.println("Year is Not a valid Year it should be between 1000 and 9999");
        System.out.println("Please Enter a valid Year: ");
        Scanner sc = new Scanner(System.in);
        year = sc.nextInt();
        validYear(year);
    }
    System.out.println("The year is Valid Year: " + year);
}
    public static void displayMenu() {


        Scanner sc = new Scanner(System.in);
        BookService service = new BookService();
        StudentService studentService = new StudentService();

        while (true) {

            System.out.println("\n====================================");
            System.out.println("     LIBRARY MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books"); // bookid -12 for Java Book has quatity of 3 udpated quantity is 2
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Register Student");
            System.out.println("7. View All Students");//studentId 15 for Ritik soni
            System.out.println("8. Search Student");
            System.out.println("9. Update Student");
            System.out.println("10. Delete Student");
            System.out.println("11. Issue Book");
            // Create a entity for issue book where table Name will be "issuedBooks" and it will have the following columns
            // in the table we will have issuedBookId (Auto incremented ), studentId (user input), bookId (user input), issueDate (todays date ), returnDate (user input). //dd/mm/yyyy or dd-mm-yyyy
            // Please Enter Student Id  - 15
            // Please Enter Book Id - 12
            // Please Enter Return date - // 31-07-2026 (dd-mm-yyyy) or (dd/mm/yyyy)
            // logical part (first Search if the student Is registered or not and we will check if the book is available or not
            //  now we will check about quantity if it is greater than 0 then only we can be able to issue the book
            //  after all the validations and logical part if everything is perfect then only we will issue the book to Student . )
            // and we will decrease the Quantity for the book .
            // and we will insert todays date for the issueDate column but we will not take the issueDate from the user and we will take the returnDate from the user and we will insert it in the returnDate column
            // and print This student has successfully issued the book and the return date is 31-07-2026
            System.out.println("12. View All Issued Books");
            // all the rows of table issuedBooks should return
            System.out.println("13. Search Issued Book");
            // we ask the user to enter the issuedBookId and we will search the issuedBooks table and return the row of that issuedBookId
            System.out.println("14. Return Issued Book");
            // we will ask the user to enter the issuedBookId and we will search the issuedBooks table and if it is found then we will delete
            // that row from the table and we will increase the quantity of that book by 1
            System.out.println("15. Exit");
            //exit will close the application.
            System.out.println("====================================");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    BookEntity book = new BookEntity();

                    sc.nextLine(); // Consume newline

                    System.out.print("Enter Book Name: ");
                    book.setBookName(sc.nextLine());

                    System.out.print("Enter Author Name: ");
                    book.setBookAuthorName(sc.nextLine());

                    System.out.print("Enter Publish Year: ");
                    int year = sc.nextInt();
                    validYear(year);
                    book.setBookPublishYear(year);

                    System.out.print("Enter Quantity: ");
                    book.setBookQuantity(sc.nextInt());

                    System.out.print("Enter Price: ");
                    book.setBookPrice(sc.nextDouble());

                    service.addBook(book);
                    break;

                case 2:

                    System.out.println("\nView All Books");
                    service.viewAllBooks();
                    break;

                case 3:

                    System.out.print("Enter Book ID: ");
                    int searchId = sc.nextInt();

                    service.searchBook(searchId);
                    break;

                case 4:

                    BookEntity updateBook = new BookEntity();

                    System.out.print("Enter Book ID: ");
                    updateBook.setBookId(sc.nextInt());

                    sc.nextLine(); // Consume newline

                    System.out.print("Enter New Book Name: ");
                    updateBook.setBookName(sc.nextLine());

                    System.out.print("Enter New Author Name: ");
                    updateBook.setBookAuthorName(sc.nextLine());

                    System.out.print("Enter New Publish Year: ");
                    int upYear = sc.nextInt();
                    validYear(upYear);
                    updateBook.setBookPublishYear(upYear);

                    System.out.print("Enter New Quantity: ");
                    updateBook.setBookQuantity(sc.nextInt());

                    System.out.print("Enter New Price: ");
                    updateBook.setBookPrice(sc.nextDouble());

                    service.updateBook(updateBook);
                    break;

                case 5:

                    System.out.print("Enter Book ID: ");
                    int deleteId = sc.nextInt();

                    service.deleteBook(deleteId);
                    break;
                case 6:
                 StudentMenu.registerStudent();
                    break;

                case 7:
                  studentService.viewAllStudents();
                    break;

                case 8:
                    StudentMenu.searchStudent();
                    break;

                case 9:
                    StudentMenu.updateStudent();
                    break;

                case 10:
                     StudentMenu.deleteStudent();
                    break;
                case 11:

                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);
                    break;

                default:

                    System.out.println("Invalid Choice! Please Try Again.");
            }
        }
    }
}