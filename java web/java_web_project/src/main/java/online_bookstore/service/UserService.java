package online_bookstore.service;

import online_bookstore.dto.UserDto;
import online_bookstore.model.User;

public interface UserService {
	
	User save (UserDto userDto);
	

}
