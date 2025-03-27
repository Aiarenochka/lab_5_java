package main.io;

public class MenuPrinter {
    public void menuOptions() {
        System.out.print("""
        --- Book Menu ---
        1. List of books by a specific author
        2. List of books by a specific publisher
        3. List of books with a minimum number of pages
        4. Save books to text file
        5. Save books to binary file
        6. Load books from text file
        7. Load books from binary file
        0. Exit
        Choose an option: >>\s""");
    }
}