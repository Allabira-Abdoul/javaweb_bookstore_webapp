package com.group1.bookstore.controller;

import java.security.Principal;

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
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{id}")
    public String addReview(@PathVariable Long id, @ModelAttribute Review review, Model model, Principal principal) {
        Book book = bookService.getBookById(id);

        review.setUser(userRepository.findByEmail(principal.getName()));

        review.setBook(book);

        reviewService.saveReview(review);
        
        book.setAverageReviews(book.getReviews().stream().mapToDouble(dreview -> dreview.getRating()).average().orElse(0.0));

        bookService.saveBook(book);

        return "redirect:/books/" + id;
    }

    @GetMapping
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());

        return "reviews";
    }

    @GetMapping("/user/{bookId}")
    public String reviewPage(@PathVariable Long bookId, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        model.addAttribute("book", bookService.getBookById(bookId));

        model.addAttribute("review", new Review());

        return "review";
    }

    @GetMapping("/{id}")
    public String getReviewById(@PathVariable Long id, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        model.addAttribute("review", reviewService.getReviewById(id));

        return "review_details";
    }

    @GetMapping("/user")
    public String getReviewsByUser(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        model.addAttribute("reviewHistory",
                reviewService.getReviewsByUser(userRepository.findByEmail(principal.getName())));

        return "review_history";
    }

    @PutMapping("/{id}")
    public String updateReview(@ModelAttribute Review review, @PathVariable Long id, Model model) {
        reviewService.updateReview(review, id);

        return "redirect:/reviews";
    }

}
