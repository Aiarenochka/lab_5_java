package main.repository;

import main.model.Book;
import java.io.*;
import java.nio.file.Files;

public class BookRepositoryBinaryImpl implements BookRepository {
    @Override
    public void saveToFile(Book[] books, File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            oos.writeObject(books);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to binary file", e);
        }
    }

    @Override
    public void saveToFile(Book[] books, String fileName) {
        saveToFile(books, new File(fileName));
    }

    @Override
    public Book[] loadFromFile(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            return (Book[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading from binary file", e);
        }
    }

    @Override
    public Book[] loadFromFile(String fileName) {
        return loadFromFile(new File(fileName));
    }
}