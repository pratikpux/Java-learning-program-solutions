package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    // Default constructor
    public BookService() {
        System.out.println("BookService default constructor called");
    }

    // Setter method for dependency injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookRepository injected into BookService via setter");
    }

    // Getter method (optional)
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    // Business methods
    public List<Book> getAllBooks() {
        if (bookRepository == null) {
            throw new IllegalStateException("BookRepository not injected");
        }
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        if (bookRepository == null) {
            throw new IllegalStateException("BookRepository not injected");
        }
        return bookRepository.findById(id);
    }

    public void addBook(Book book) {
        if (bookRepository == null) {
            throw new IllegalStateException("BookRepository not injected");
        }
        bookRepository.save(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void displayAvailableBooks() {
        if (bookRepository == null) {
            throw new IllegalStateException("BookRepository not injected");
        }

        System.out.println("\n=== Available Books ===");
        bookRepository.findAll().stream()
                .filter(Book::isAvailable)
                .forEach(System.out::println);
    }

    public void displayAllBooks() {
        if (bookRepository == null) {
            throw new IllegalStateException("BookRepository not injected");
        }

        System.out.println("\n=== All Books ===");
        bookRepository.findAll().forEach(System.out::println);
    }
}