package com.group1.bookstore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group1.bookstore.Dto.UserDto;
import com.group1.bookstore.model.Book;
import com.group1.bookstore.model.User;
import com.group1.bookstore.service.BookService;
import com.group1.bookstore.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    BookService bookService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("dto", new UserDto());
        return "register";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto dto, Model model) {
        userService.saveUser(User.toUser(dto));
        model.addAttribute("message", "Registered Successfuly!");
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/user-page")
    public String userPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        model.addAttribute("book", new Book());

        return "user";
    }

    @GetMapping("/admin-page")
    public String adminPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        userDetails.getAuthorities().stream().map(r -> r.getAuthority()).findFirst();
        model.addAttribute("user", userDetails);
        return "admin";
    }
}
