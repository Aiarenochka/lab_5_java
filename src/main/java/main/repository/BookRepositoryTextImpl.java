package main.repository;

import main.model.Book;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

public class BookRepositoryTextImpl implements BookRepository {
    @Override
    public void saveToFile(Book[] books, File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            for (Book book : books) {
                writer.write(String.format("%d|%s|%s|%s|%d%n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublisher(),
                        book.getPages()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to text file", e);
        }
    }

    @Override
    public void saveToFile(Book[] books, String fileName) {
        saveToFile(books, new File(fileName));
    }

    @Override
    public Book[] loadFromFile(File file) {
        try (Stream<String> lines = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            return lines.map(line -> line.split("\\|"))
                    .filter(parts -> parts.length == 5)
                    .map(parts -> new Book(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            parts[3],
                            Integer.parseInt(parts[4])))
                    .toArray(Book[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Error reading from text file", e);
        }
    }

    @Override
    public Book[] loadFromFile(String fileName) {
        return loadFromFile(new File(fileName));
    }
}