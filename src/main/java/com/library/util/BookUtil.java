package com.library.util;

import java.util.Scanner;

import com.library.entity.BookEntity;

public class BookUtil {

    static Scanner sc = new Scanner(System.in);

    public static BookEntity getBookDetails() {

        BookEntity book = new BookEntity();

        System.out.print("Enter Book ID: ");
        book.setBookId(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter Book Name: ");
        book.setBookName(sc.nextLine());

        System.out.print("Enter Author Name: ");
        book.setBookAuthorName(sc.nextLine());

        System.out.print("Enter Publish Year: ");
        book.setBookPublishYear(sc.nextInt());

        System.out.print("Enter Quantity: ");
        book.setBookQuantity(sc.nextInt());

        System.out.print("Enter Book Price: ");
        book.setBookPrice(sc.nextDouble());

        return book;
    }

//    public static int getBookId() {
//
//        System.out.print("Enter Book ID: ");
//        return sc.nextInt();
//    }
}