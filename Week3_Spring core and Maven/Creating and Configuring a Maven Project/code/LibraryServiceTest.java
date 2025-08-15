package com.example.library;

import com.example.library.model.Book;
import com.example.library.service.LibraryService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryServiceTest {

    private LibraryService libraryService;

    @Before
    public void setUp() {
        libraryService = new LibraryService();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("1111", "Java Basics", "John Doe", "TechPress",
                LocalDate.now(), "Programming", 5, 299.99);
        Book added = libraryService.addBook(book);

        assertNotNull(added.getId());
        assertEquals("Java Basics", added.getTitle());
    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book("1111", "Java", "Author A", "Publisher", LocalDate.now(), "Tech", 5, 100);
        Book book2 = new Book("2222", "Python", "Author B", "Publisher", LocalDate.now(), "Tech", 6, 150);

        libraryService.addBook(book1);
        libraryService.addBook(book2);

        List<Book> books = libraryService.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    public void testGetBookById() {
        Book book = new Book("3333", "Spring in Action", "Craig Walls", "Manning",
                LocalDate.of(2020, 5, 1), "Java", 10, 599.0);
        Book added = libraryService.addBook(book);

        Book found = libraryService.getBookById(added.getId());
        assertNotNull(found);
        assertEquals("Spring in Action", found.getTitle());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("4444", "To Delete", "Author", "Publisher",
                LocalDate.now(), "Test", 3, 50);
        Book added = libraryService.addBook(book);

        boolean deleted = libraryService.deleteBook(added.getId());
        assertTrue(deleted);
        assertNull(libraryService.getBookById(added.getId()));
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("5555", "Old Title", "Author", "Publisher",
                LocalDate.now(), "Category", 4, 120.0);
        Book added = libraryService.addBook(book);

        added.setTitle("Updated Title");
        added.setAvailableCopies(2);

        Book updated = libraryService.updateBook(added);

        assertNotNull(updated);
        assertEquals("Updated Title", updated.getTitle());
        assertEquals(2, updated.getAvailableCopies());
    }

    @Test
    public void testUpdateBook_NotFound() {
        Book book = new Book("9999", "Non-existent", "Nobody", "NoPub",
                LocalDate.now(), "None", 0, 0.0);
        book.setId(999L); // Not yet added, so should not be found

        Book result = libraryService.updateBook(book);
        assertNull(result);
    }
}
