package com.group1.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.group1.bookstore.model.Book;
import com.group1.bookstore.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public String addBook(@ModelAttribute Book book, Model model) {
        bookService.saveBook(book);

        return "redirect:/books";
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        
        return "books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));

        return "book_details";
    }

    @GetMapping("/title/{title}")
    public String getBookByTitle(@PathVariable String title, Model model) {
        model.addAttribute("book", bookService.getBookByTitle(title));

        return "book_details";
    }

    @GetMapping("/author/{author}")
    public String getBooksByAuthor(@PathVariable String author, Model model) {
        model.addAttribute("books", bookService.getBooksByAuthor(author));

        return "books";
    }

    @GetMapping("/genre/{genre}")
    public String getBooksByGenre(@PathVariable String genre, Model model) {
        model.addAttribute("books", bookService.getBooksByGenre(genre));

        return "books";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, Model model) {
        bookService.updateBook(book, id);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        bookService.deleteBook(id);

        return "redirect:/books";
    }
    
}
