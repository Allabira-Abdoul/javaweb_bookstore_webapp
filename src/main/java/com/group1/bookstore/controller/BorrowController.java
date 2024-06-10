package com.group1.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.group1.bookstore.model.*;
import com.group1.bookstore.service.*;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
        
        @Autowired
        private BorrowService borrowService;

        @Autowired
        private BookService bookService;
    
        @PostMapping
        public String addBorrow(@ModelAttribute Borrow borrow, Model model) {

            Book book = bookService.getBookById(borrow.getBookId());

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
    
        @GetMapping("/{id}")
        public String getBorrowById(@PathVariable Long id, Model model) {
            model.addAttribute("borrow", borrowService.getBorrowById(id));
    
            return "borrow_details";
        }
    
        @GetMapping("/user/{userId}")
        public String getBorrowsByUserId(@PathVariable Long userId, Model model) {
            model.addAttribute("borrows", borrowService.getBorrowsByUserId(userId));
    
            return "borrows";
        }
    
        @PutMapping("/{id}")
        public String updateBorrow(@ModelAttribute Borrow borrow, @PathVariable Long id, Model model) {
            borrowService.updateBorrow(borrow, id);
    
            return "redirect:/borrows";
        }
    
}
