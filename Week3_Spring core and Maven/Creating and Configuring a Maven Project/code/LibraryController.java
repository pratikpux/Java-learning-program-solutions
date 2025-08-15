package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", libraryService.getAllBooks());
        return "index";
    }

    @GetMapping("/books")
    @ResponseBody
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @PostMapping("/books")
    @ResponseBody
    public Book addBook(@RequestBody Book book) {
        return libraryService.addBook(book);
    }

    @GetMapping("/books/{id}")
    @ResponseBody
    public Book getBook(@PathVariable Long id) {
        return libraryService.getBookById(id);
    }

    @DeleteMapping("/books/{id}")
    @ResponseBody
    public boolean deleteBook(@PathVariable Long id) {
        return libraryService.deleteBook(id);
    }
}