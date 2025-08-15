package com.library.repository;

import com.library.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> books;

    public BookRepository() {
        books = new ArrayList<>();
        // Initialize with some sample data
        books.add(new Book(1, "Spring in Action", "Craig Walls", true));
        books.add(new Book(2, "Java: The Complete Reference", "Herbert Schildt", true));
        books.add(new Book(3, "Clean Code", "Robert Martin", false));
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Book book) {
        books.add(book);
    }

    public void deleteById(int id) {
        books.removeIf(book -> book.getId() == id);
    }

    public void displayRepository() {
        System.out.println("BookRepository initialized with " + books.size() + " books");
    }
}