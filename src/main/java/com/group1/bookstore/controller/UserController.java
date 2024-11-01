package com.group1.bookstore.controller;


import java.security.Principal;
import java.util.List;

import com.group1.bookstore.Dto.UserDto;
import com.group1.bookstore.model.Book;
import com.group1.bookstore.model.User;
import com.group1.bookstore.service.BookService;
import com.group1.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;


	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "register";
	}

	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
		userService.save(userDto);
		model.addAttribute("message", "Registered Successfuly!");
		return "register";
	}

	@GetMapping("/login")
	public String login() {
		return "register";
	}

	@GetMapping("user-page")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);

		List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

		return "user";
	}

	@GetMapping("/User-detail")
	public String showAllBooks(Model model) {
		List<User> users = userService.getUsersByRole("USER");
		model.addAttribute("users", users);
		return "user_detail";
	}
	@PostMapping("/user/delete")
	public String deleteUser(@RequestParam("id") Long userId) {
		userService.deleteUserById(userId);
		return "redirect:/User-detail";
	}

	@GetMapping("/user/details")
	public String editUser(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);

		return "user_edit";
	}
	@PostMapping("/place_order")
	public String placeOrder(Model model, Principal principal) {
		return "redirect:/user"; // Make sure "Place_Order.html" exists in your templates folder
	}
}

