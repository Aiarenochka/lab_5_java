package main.repository;

import java.io.File;

public interface Repository<T> {
    void saveToFile(T[] items, File file);
    void saveToFile(T[] items, String fileName);
    T[] loadFromFile(File file);
    T[] loadFromFile(String fileName);
}