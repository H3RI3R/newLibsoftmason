package com.library.menu;

import java.util.Scanner;

import com.library.entity.BookEntity;
import com.library.service.BookService;

public class BookMenu {

    public static void displayMenu() {


        Scanner sc = new Scanner(System.in);
        BookService service = new BookService();

        while (true) {

            System.out.println("\n====================================");
            System.out.println("     LIBRARY MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
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
                    book.setBookPublishYear(sc.nextInt());

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
                    updateBook.setBookPublishYear(sc.nextInt());

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