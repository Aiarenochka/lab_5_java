package main.controller;

import main.io.*;
import main.model.Book;
import main.repository.BookRepository;
import main.repository.BookRepositoryBinaryImpl;
import main.repository.BookRepositoryTextImpl;
import main.service.BookService;
import java.util.Scanner;

public class BookController {
    private final BookService service = new BookService();
    private final View view = new View();
    private final Scanner scanner = new Scanner(System.in);
    private final MenuPrinter menuOptions = new MenuPrinter();
    private final BookRepository textRepo = new BookRepositoryTextImpl();
    private final BookRepository binaryRepo = new BookRepositoryBinaryImpl();

    public void processBooks(Book[] initialBooks) {
        label:
        while (true) {
            menuOptions.menuOptions();
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1 -> {
                    view.prompt("Enter author name: ");
                    if (initialBooks != null) {
                        view.showBooks(service.findBooksByAuthor(initialBooks, scanner.nextLine()));
                    }
                }
                case 2 -> {
                    view.prompt("Enter publisher: ");
                    if (initialBooks != null) {
                        view.showBooks(service.findBooksByPublisher(initialBooks, scanner.nextLine()));
                    }
                }
                case 3 -> {
                    view.prompt("Enter min pages: ");
                    if (initialBooks != null) {
                        view.showBooks(service.findBooksByMinPages(initialBooks, scanner.nextInt()));
                    }
                    scanner.nextLine();
                }
                case 4 -> {
                    view.prompt("Enter text filename: ");
                    textRepo.saveToFile(initialBooks, scanner.nextLine());
                    view.prompt("Books saved successfully!");
                }
                case 5 -> {
                    view.prompt("Enter binary filename: ");
                    binaryRepo.saveToFile(initialBooks, scanner.nextLine());
                    view.prompt("Books saved successfully!");
                }
                case 6 -> {
                    view.prompt("Load from text file: ");
                    initialBooks = textRepo.loadFromFile(scanner.nextLine());
                    if (initialBooks != null) {
                        view.prompt("Books loaded successfully!");
                        view.showBooks(initialBooks);
                    }
                    else {view.prompt("Books loaded failed!");}

                }
                case 7 -> {
                    view.prompt("Load from binary file: ");
                    initialBooks = binaryRepo.loadFromFile(scanner.nextLine());
                    if (initialBooks != null) {
                        view.prompt("Books loaded successfully!");
                        view.showBooks(initialBooks);
                    } else {
                        view.prompt("Books loaded failed!");
                    }
                }
                case 0 -> {
                    break label;
                }
                default -> view.prompt("Invalid option, try again.");
            }
        }
    }
}