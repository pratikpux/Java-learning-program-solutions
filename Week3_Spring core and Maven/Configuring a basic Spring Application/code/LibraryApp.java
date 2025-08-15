package com.library;

import com.library.service.BookService;
import com.library.model.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class LibraryApp {
    public static void main(String[] args) {
        System.out.println("Starting Library Management System...");
        System.out.println("=====================================");

        // Load Spring Application Context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get BookService bean from Spring context
        BookService bookService = (BookService) context.getBean("bookService");

        System.out.println("\n1. Testing Spring Configuration:");
        System.out.println("BookService bean loaded successfully: " + true);
        System.out.println("BookRepository injected: " + (bookService.getBookRepository() != null));

        System.out.println("\n2. Displaying All Books:");
        System.out.println("========================");
        List<Book> allBooks = bookService.getAllBooks();
        allBooks.forEach(System.out::println);

        System.out.println("\n3. Searching for Available Books:");
        System.out.println("=================================");
        List<Book> availableBooks = bookService.getAvailableBooks();
        availableBooks.forEach(System.out::println);

        System.out.println("\n4. Testing Book Operations:");
        System.out.println("===========================");

        // Display book information
        System.out.println("\nBook Details for ID 1:");
        bookService.displayBookInfo(1L);

        // Borrow a book
        System.out.println("\nBorrowing book with ID 1:");
        boolean borrowed = bookService.borrowBook(1L);
        System.out.println("Borrow successful: " + borrowed);

        // Try to borrow the same book again
        System.out.println("\nTrying to borrow the same book again:");
        boolean borrowedAgain = bookService.borrowBook(1L);
        System.out.println("Borrow successful: " + borrowedAgain);

        // Return the book
        System.out.println("\nReturning book with ID 1:");
        bookService.returnBook(1L);

        // Search books by title
        System.out.println("\n5. Searching Books by Title:");
        System.out.println("============================");
        List<Book> searchResults = bookService.searchBooksByTitle("Great");
        searchResults.forEach(System.out::println);

        // Add a new book
        System.out.println("\n6. Adding a New Book:");
        System.out.println("=====================");
        Book newBook = new Book(5L, "Spring in Action", "Craig Walls", "978-1-61729-120-3", true);
        bookService.addBook(newBook);

        // Display all books again
        System.out.println("\nAll Books After Adding New Book:");
        System.out.println("================================");
        List<Book> updatedBooks = bookService.getAllBooks();
        updatedBooks.forEach(System.out::println);

        System.out.println("\n7. Final Available Books:");
        System.out.println("=========================");
        List<Book> finalAvailableBooks = bookService.getAvailableBooks();
        finalAvailableBooks.forEach(System.out::println);

        System.out.println("\nLibrary Management System Demo Completed!");

        // Close the context
        ((ClassPathXmlApplicationContext) context).close();
    }
}