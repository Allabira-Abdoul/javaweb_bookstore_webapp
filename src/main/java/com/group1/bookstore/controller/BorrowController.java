package com.group1.bookstore.controller;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.group1.bookstore.model.*;
import com.group1.bookstore.repository.UserRepository;
import com.group1.bookstore.service.*;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{id}")
    public String addBorrow(@PathVariable Long id, @ModelAttribute Borrow borrow, Model model, Principal principal) {
        Book book = bookService.getBookById(id);

        borrow.setUser(userRepository.findByEmail(principal.getName()));

        borrow.setBook(book);

        Date sqlDate = borrow.getBorrowDate();

        LocalDate localDate = sqlDate.toLocalDate();

        LocalDate newDate = localDate.plusDays(7);

        Date newSqlDate = java.sql.Date.valueOf(newDate);

        borrow.setReturnDate(newSqlDate);

        borrow.setReturned(false);

        book.setAmount(book.getAmount() - 1);

        bookService.saveBook(book);

        borrowService.saveBorrow(borrow);

        return "redirect:/user-page";
    }

    @GetMapping
    public String getAllBorrows(Model model) {
        model.addAttribute("borrows", borrowService.getAllBorrows());

        return "borrows";
    }

    @GetMapping("/user/{bookId}")
    public String borrowPage(@PathVariable Long bookId, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        model.addAttribute("book", bookService.getBookById(bookId));

        model.addAttribute("borrow", new Borrow());

        return "borrow";
    }

    @GetMapping("/{id}")
    public String getBorrowById(@PathVariable Long id, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        model.addAttribute("borrow", borrowService.getBorrowById(id));

        return "borrow_details";
    }

    @GetMapping("/user")
    public String getBorrowsByUser(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        model.addAttribute("borrowHistory",
                borrowService.getBorrowsByUser(userRepository.findByEmail(principal.getName())));

        return "borrow_history";
    }

    @PutMapping("/{id}")
    public String updateBorrow(@ModelAttribute Borrow borrow, @PathVariable Long id, Model model) {
        borrowService.updateBorrow(borrow, id);

        return "redirect:/borrows";
    }

}
