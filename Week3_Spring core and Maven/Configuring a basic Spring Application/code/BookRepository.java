package com.library.repository;

import com.library.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private String repositoryName;
    private List<Book> books;

    // Default constructor
    public BookRepository() {
        this.books = new ArrayList<>();
        initializeData();
    }

    // Initialize with sample data
    private void initializeData() {
        books.add(new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5", true));
        books.add(new Book(2L, "To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", true));
        books.add(new Book(3L, "1984", "George Orwell", "978-0-452-28423-4", false));
        books.add(new Book(4L, "Pride and Prejudice", "Jane Austen", "978-0-14-143951-8", true));
    }

    // Find all books
    public List<Book> findAll() {
        System.out.println("Repository: Fetching all books from " + repositoryName);
        return new ArrayList<>(books);
    }

    // Find book by ID
    public Book findById(Long id) {
        System.out.println("Repository: Searching for book with ID " + id);
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Find books by title
    public List<Book> findByTitle(String title) {
        System.out.println("Repository: Searching for books with title containing: " + title);
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    // Find available books
    public List<Book> findAvailableBooks() {
        System.out.println("Repository: Fetching available books");
        return books.stream()
                .filter(Book::isAvailable)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    // Save book
    public void save(Book book) {
        System.out.println("Repository: Saving book - " + book.getTitle());
        books.add(book);
    }

    // Update book availability
    public void updateAvailability(Long id, boolean available) {
        Book book = findById(id);
        if (book != null) {
            book.setAvailable(available);
            System.out.println("Repository: Updated availability for book ID " + id + " to " + available);
        }
    }

    // Getter and Setter for repositoryName
    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }
}