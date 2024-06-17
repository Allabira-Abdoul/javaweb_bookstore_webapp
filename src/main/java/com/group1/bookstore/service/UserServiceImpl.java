package com.group1.bookstore.service;

import com.group1.bookstore.Dto.UserDto;
import com.group1.bookstore.model.User;
import com.group1.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getRole(), userDto.getFullname());
		return userRepository.save(user);
	}

	@Override
	public List<User> getUsersByRole(String role) {
		return userRepository.findByRole(role);
	}
	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	@Override
	public void sendDeleteNotification(User user) {
		String subject = "Account Deletion Notification";
		String body = "Dear " + user.getFullname() + ",\n\nYour account with email " + user.getEmail() + " has been deleted from our system.\n\nThank you.";
		emailService.sendEmail(user.getEmail(), subject, body);
	}

}
