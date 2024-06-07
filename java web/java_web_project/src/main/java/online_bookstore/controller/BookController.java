package online_bookstore.controller;


import online_bookstore.model.Book;
import online_bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        super();
        this.bookService = bookService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/contacts")
    public String showAllContacts(Model model) {
        List<Book> contacts = bookService.getAllBooks();
        model.addAttribute("contacts", contacts);
        return ""; // Return the HTML template for displaying all events
    }


    @GetMapping("/contacts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("Book", new Book());
        return "";
    }

    @PostMapping("/contacts/create")
    public String createContact(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        try {
            bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("message", "Book created successfully!");
            return "redirect:/contacts";
        } catch (Exception e) {
            // Handle error
            redirectAttributes.addFlashAttribute("error", "Failed to create Book: " + e.getMessage());
            return "redirect:/contacts";
        }
    }


    @GetMapping("/contacts/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return ""; // Return the HTML template for editing an event
    }
    @PostMapping("/contacts/update")
    public String updateBook(@ModelAttribute Book book) {
        bookService.updateBook(book);
        return "redirect:/contacts"; // Redirect to the events page after updating the event
    }

    @PostMapping("/Contacts/{id}")
    public String updateContact(@PathVariable Long id,
                                @ModelAttribute("book") Book book,
                                Model model) {

        Book existingBook = bookService.getBookById(id);
        existingBook.setId(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setDescription(book.getDescription());
        existingBook.setGenre(book.getGenre());
        existingBook.setCoverImageUrl(book.getCoverImageUrl());
        existingBook.setAvailable(book.isAvailable());

        bookService.updateBook(existingBook);
        return "redirect:/contact";
    }


    @PostMapping("/contacts/delete")
    public String deleteEvent(@RequestParam ("id") Long bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/contacts"; // Redirect to the events page after deletion
    }

}
