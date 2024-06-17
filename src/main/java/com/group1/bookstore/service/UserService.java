package com.group1.bookstore.service;


import com.group1.bookstore.Dto.UserDto;
import com.group1.bookstore.model.Book;
import com.group1.bookstore.model.User;

import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;


public interface UserService {

	User save (UserDto userDto);
	List<User> getUsersByRole(String role);
	void deleteUserById(Long id);



}





