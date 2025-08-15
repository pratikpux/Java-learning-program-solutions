package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    // Default constructor
    public BookService() {
    }

    // Constructor with BookRepository
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books
    public List<Book> getAllBooks() {
        System.out.println("Service: Getting all books");
        return bookRepository.findAll();
    }

    // Get book by ID
    public Book getBookById(Long id) {
        System.out.println("Service: Getting book by ID " + id);
        return bookRepository.findById(id);
    }

    // Search books by title
    public List<Book> searchBooksByTitle(String title) {
        System.out.println("Service: Searching books by title: " + title);
        return bookRepository.findByTitle(title);
    }

    // Get available books
    public List<Book> getAvailableBooks() {
        System.out.println("Service: Getting available books");
        return bookRepository.findAvailableBooks();
    }

    // Add new book
    public void addBook(Book book) {
        System.out.println("Service: Adding new book - " + book.getTitle());
        bookRepository.save(book);
    }

    // Borrow book
    public boolean borrowBook(Long id) {
        System.out.println("Service: Attempting to borrow book with ID " + id);
        Book book = bookRepository.findById(id);
        if (book != null && book.isAvailable()) {
            bookRepository.updateAvailability(id, false);
            System.out.println("Service: Book borrowed successfully - " + book.getTitle());
            return true;
        } else {
            System.out.println("Service: Book not available for borrowing");
            return false;
        }
    }

    // Return book
    public void returnBook(Long id) {
        System.out.println("Service: Returning book with ID " + id);
        Book book = bookRepository.findById(id);
        if (book != null) {
            bookRepository.updateAvailability(id, true);
            System.out.println("Service: Book returned successfully - " + book.getTitle());
        }
    }

    // Display book information
    public void displayBookInfo(Long id) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            System.out.println("Book Information:");
            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
        } else {
            System.out.println("Book not found with ID: " + id);
        }
    }

    // Getter and Setter for bookRepository
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}