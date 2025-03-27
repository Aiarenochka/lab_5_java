package main.factory;

import main.model.Book;

public class BookFactory {
    public static Book[] createBooks() {
        return new Book[]{
                new Book(1, "The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company", 277,1951),
                new Book(2, "1984", "George Orwell", "Secker & Warburg", 328, 1949),
                new Book(3, "To Kill a Mockingbird", "Harper Lee", "J.B. Lippincott & Co.", 281, 1960),
                new Book(4, "Brave New World", "Aldous Huxley", "Chatto & Windus", 311, 1939),
                new Book(5, "Fahrenheit 451", "Ray Bradbury", "Ballantine Books", 249, 1953)
        };
    }
}