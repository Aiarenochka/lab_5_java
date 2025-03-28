package main.repository;

import com.google.gson.*;
import main.model.Book;

import java.io.*;

public class BookRepositoryJSONImpl implements BookRepository {
    @Override
    public void saveToFile(Book[] items, File file) {
        try (Writer writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(items, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveToFile(Book[] items, String fileName) {
        File file = new File(fileName);
        saveToFile(items, file);
    }

    @Override
    public Book[] loadFromFile(File file) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(new FileReader(file), Book[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book[] loadFromFile(String fileName) {
        File file = new File(fileName);
        return loadFromFile(file);
    }
}
