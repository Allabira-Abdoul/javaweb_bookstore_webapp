package com.group1.bookstore.controller;

import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Autowired
    private UserDetailsService userDetailsService;

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

    @GetMapping("/filter")
    public String FilteredBooks(@RequestParam(name = "genre") String genre, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        if (genre.isEmpty()) {
            model.addAttribute("books", bookService.getAllBooks());
        } else {
            model.addAttribute("books", bookService.getBooksByGenre(genre));
        }

        return "user";
    }

    @GetMapping("/search")
    public String SearchedBooks(@RequestParam(name = "title") String title, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        if (title.isBlank()) {
            List<Book> books = bookService.getAllBooks();
            model.addAttribute("books", books);
        } else {
            Set<Book> books = new HashSet<>();

            List<Book> books1 = bookService.getBookByTitleContaining(title);
            for (Book book : books1) {
                books.add(book);
            }
            List<Book> books2 = bookService.getBooksByAuthorContaining(title);
            for (Book book : books2) {
                books.add(book);
            }
            model.addAttribute("books", books);

        }

        return "user";
    }

}
