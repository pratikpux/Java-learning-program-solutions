package com.example.library.service;

import com.example.library.model.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public Book addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }

    public Book updateBook(Book updatedBook) {
        Book existingBook = getBookById(updatedBook.getId());
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setAvailableCopies(updatedBook.getAvailableCopies()); // ✅ Fixed line
            return existingBook;
        }
        return null;
    }
}
