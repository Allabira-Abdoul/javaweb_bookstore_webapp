package com.group1.bookstore.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group1.bookstore.model.Book;
import com.group1.bookstore.service.BookService;

@Controller
public class BookController {
    private BookService bookService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public BookController(BookService bookService) {
        super();
        this.bookService = bookService;
    }

    @GetMapping("/Book")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/Book/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "create_book";
    }

    @PostMapping("/Book/create")
    public String createBook(@ModelAttribute Book book,
                             @RequestParam("coverImage") MultipartFile coverImage,
                             RedirectAttributes redirectAttributes) {
        if (!coverImage.isEmpty()) {
            try {
                byte[] bytes = coverImage.getBytes();
                Path path = Paths.get(uploadDir + coverImage.getOriginalFilename());
                Files.write(path, bytes);
                book.setCoverImageUrl(path.toString());
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Failed to upload cover image");
                return "redirect:/Book/create";
            }
        }

        try {
            bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("message", "Book created successfully!");
            return "redirect:/Book";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to create Book: " + e.getMessage());
            return "redirect:/Book";
        }
    }

    @GetMapping("/book/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit_book";
    }
    @GetMapping("/book/view/{id}")
    public String showViewForm(@PathVariable Long id, Model model) {
        Book books = bookService.getBookById(id);
        model.addAttribute("book", books);
        return "View_book"; // Return the HTML template for viewing a task
    }

    @PostMapping("/Book/update")
    public String updateBook(@ModelAttribute Book book) {
        bookService.updateBook(book);
        return "redirect:/Book";
    }

    @PostMapping("/Book/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute("book") Book book,
                             Model model) {
        Book existingBook = bookService.getBookById(id);
        existingBook.setId(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        existingBook.setAmount(book.getAmount());
        existingBook.setDescription(book.getDescription());
        existingBook.setAvailable(book.getAvailable());
        existingBook.setCoverImageUrl(book.getCoverImageUrl());
        bookService.updateBook(existingBook);
        return "redirect:/Book";
    }

    @PostMapping("/Book/delete")
    public String deleteBook(@RequestParam("id") Long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/Book";
    }
}



//    @Autowired
//    private BookService bookService;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @PostMapping
//    public String addBook(@ModelAttribute Book book, Model model) {
//        bookService.saveBook(book);
//
//        return "redirect:/books";
//    }
//    @GetMapping("/book")
//    public String showAllContacts(Model model) {
//        List<Book> books = bookService.getAllBooks();
//        model.addAttribute("Book", books);
//        return "admin"; // Return the HTML template for displaying all events
//    }
//    @GetMapping("/book/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("Book", new Book());
//        return "admin";
//    }
//
//    @GetMapping
//    public String getAllBooks(Model model) {
//        model.addAttribute("books", bookService.getAllBooks());
//
//        return "books";
//    }
//
//    @GetMapping("/{id}")
//    public String getBookById(@PathVariable Long id, Model model) {
//        model.addAttribute("book", bookService.getBookById(id));
//
//        return "book_details";
//    }
//
//    @GetMapping("/title/{title}")
//    public String getBookByTitle(@PathVariable String title, Model model) {
//        model.addAttribute("book", bookService.getBookByTitle(title));
//
//        return "book_details";
//    }
//
//    @GetMapping("/author/{author}")
//    public String getBooksByAuthor(@PathVariable String author, Model model) {
//        model.addAttribute("books", bookService.getBooksByAuthor(author));
//
//        return "books";
//    }
//
//    @GetMapping("/genre/{genre}")
//    public String getBooksByGenre(@PathVariable String genre, Model model) {
//        model.addAttribute("books", bookService.getBooksByGenre(genre));
//
//        return "books";
//    }
//
//    @PutMapping("/{id}")
//    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, Model model) {
//        bookService.updateBook(book, id);
//
//        return "redirect:/books";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteBook(@PathVariable Long id, Model model) {
//        bookService.deleteBook(id);
//
//        return "redirect:/books";
//    }
//
//    @GetMapping("/filter")
//    public String FilteredBooks(@RequestParam(name = "genre") String genre, Model model, Principal principal) {
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        model.addAttribute("user", userDetails);
//
//        if (genre.isEmpty()) {
//            model.addAttribute("books", bookService.getAllBooks());
//        } else {
//            model.addAttribute("books", bookService.getBooksByGenre(genre));
//        }
//
//        return "user";
//    }
//
//    @GetMapping("/search")
//    public String SearchedBooks(@RequestParam(name = "title") String title, Model model, Principal principal) {
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        model.addAttribute("user", userDetails);
//
//        if (title.isBlank()) {
//            List<Book> books = bookService.getAllBooks();
//            model.addAttribute("books", books);
//        } else {
//            Set<Book> books = new HashSet<>();
//
//            List<Book> books1 = bookService.getBookByTitleContaining(title);
//            for (Book book : books1) {
//                books.add(book);
//            }
//            List<Book> books2 = bookService.getBooksByAuthorContaining(title);
//            for (Book book : books2) {
//                books.add(book);
//            }
//            model.addAttribute("books", books);
//
//        }
//
//        return "user";
//    }
//
//}
