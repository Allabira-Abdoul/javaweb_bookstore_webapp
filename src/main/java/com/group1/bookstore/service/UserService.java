package com.group1.bookstore.service;

import com.group1.bookstore.model.User;

public interface UserService {
    public User saveUser(User user);
    public User getUserById(Long id);
    public User getUserByUsername(String username);
    public User getUserByEmail(String email);
    public User getUserByUsernameOrEmail(String username, String email);
    public User updateUser(User user, Long id);
    public void deleteUser(Long id);
}
