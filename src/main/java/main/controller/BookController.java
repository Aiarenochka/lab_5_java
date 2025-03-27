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
        Book[] currentBooks = initialBooks.clone(); // Работаем с копией массива
        label:
        while (true) {
            menuOptions.menuOptions();
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1 -> {
                    System.out.print("Enter author name: ");
                    view.showBooks(service.findBooksByAuthor(currentBooks, scanner.nextLine()));
                }
                case 2 -> {
                    System.out.print("Enter publisher: ");
                    view.showBooks(service.findBooksByPublisher(currentBooks, scanner.nextLine()));
                }
                case 3 -> {
                    System.out.print("Enter min pages: ");
                    view.showBooks(service.findBooksByMinPages(currentBooks, scanner.nextInt()));
                    scanner.nextLine();
                }
                case 4 -> {
                    System.out.print("Enter text filename: ");
                    textRepo.saveToFile(currentBooks, scanner.nextLine());
                    System.out.println("Books saved successfully!");
                }
                case 5 -> {
                    System.out.print("Enter binary filename: ");
                    binaryRepo.saveToFile(currentBooks, scanner.nextLine());
                    System.out.println("Books saved successfully!");
                }
                case 6 -> {
                    System.out.print("Load from text file: ");
                    currentBooks = textRepo.loadFromFile(scanner.nextLine());
                    System.out.println("Books loaded successfully!");
                    view.showBooks(currentBooks);
                }
                case 7 -> {
                    System.out.print("Load from binary file: ");
                    currentBooks = binaryRepo.loadFromFile(scanner.nextLine());
                    System.out.println("Books loaded successfully!");
                    view.showBooks(currentBooks);
                }
                case 0 -> {
                    break label;
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }
}