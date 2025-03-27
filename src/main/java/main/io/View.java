package main.io;

import main.model.Book;

public class View {
    public void showBooks(Book[] books) {
        if (books.length == 0) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}