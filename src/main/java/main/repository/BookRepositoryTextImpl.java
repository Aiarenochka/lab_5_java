package main.repository;

import main.model.Book;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


public class BookRepositoryTextImpl implements BookRepository {
    @Override
    public void saveToFile(Book[] books, File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            for (Book book : books) {
                writer.write(String.format("%d|%s|%s|%s|%d|%d%n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublisher(),
                        book.getPages(),
                        book.getYear()));
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
    public Book[] loadFromFile(File file){
        int size = 5, counter = 0;
        Book[] books = new Book[size];
        try(BufferedReader in = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            String line;
            while((line = in.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    books[counter] = new Book(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            parts[3],
                            Integer.parseInt(parts[4]),
                            Integer.parseInt(parts[5])
                    );
                }
                counter++;
            }
        }catch (IOException e){
            System.out.println("File not found");
        }
        return books;
    }


    @Override
    public Book[] loadFromFile(String fileName) {
        return loadFromFile(new File(fileName));
    }
}