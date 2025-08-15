package com.example.library;

import com.example.library.service.LibraryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("✅ Library Management System Started Successfully!");

        // Test Spring bean creation
        LibraryService libraryService = context.getBean(LibraryService.class);
        System.out.println("🧩 LibraryService bean created: " + libraryService);

        // Optional: Add a book and print it
        libraryService.addBook(
                new com.example.library.model.Book("9780134685991", "Effective Java", "Joshua Bloch",
                        "Addison-Wesley", java.time.LocalDate.of(2018, 1, 6), "Programming", 10, 45.99)
        );

        System.out.println("📚 Books in library: " + libraryService.getAllBooks());
    }
}
