package main.service;

import main.model.Book;
import java.util.Arrays;

public class BookService {
    public Book[] findBooksByAuthor(Book[] books, String author) {
        Book[] resultList = new Book[books.length];
        int counter = 0;

        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                resultList[counter] = book;
                counter++;
            }
        }

        return Arrays.copyOf(resultList, counter);
    }

    public Book[] findBooksByPublisher(Book[] books, String publisher) {
        Book[] resultList = new Book[books.length];
        int counter = 0;

        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisher)) {
                resultList[counter] = book;
                counter++;
            }
        }

        return Arrays.copyOf(resultList, counter);
    }

    public Book[] findBooksByMinPages(Book[] books, int minPages) {
        Book[] resultList = new Book[books.length];
        int counter = 0;

        for (Book book : books) {
            if (book.getPages() >= minPages) {
                resultList[counter] = book;
                counter++;
            }
        }

        return Arrays.copyOf(resultList, counter);
    }
}