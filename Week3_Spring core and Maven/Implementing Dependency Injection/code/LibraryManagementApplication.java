package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        System.out.println("=== Library Management System Starting ===");

        // Load Spring configuration - FIXED: Remove com.library prefix
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get the BookService bean (with BookRepository injected)
        BookService bookService = context.getBean("bookService", BookService.class);

        // Test the dependency injection
        System.out.println("\n=== Testing Dependency Injection ===");

        // Display all books
        bookService.displayAllBooks();

        // Display available books
        bookService.displayAvailableBooks();

        // Add a new book
        Book newBook = new Book(4, "Spring Boot in Action", "Craig Walls", true);
        bookService.addBook(newBook);

        // Display all books again to see the new book
        bookService.displayAllBooks();

        // Test individual book retrieval
        System.out.println("\n=== Testing Book Retrieval ===");
        Book book = bookService.getBookById(1);
        if (book != null) {
            System.out.println("Found book: " + book);
        } else {
            System.out.println("Book not found");
        }

        System.out.println("\n=== Application Completed Successfully ===");

        // Close the context
        ((ClassPathXmlApplicationContext) context).close();
    }
}