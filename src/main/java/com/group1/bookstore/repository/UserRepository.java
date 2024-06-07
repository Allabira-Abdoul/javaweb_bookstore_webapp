package com.group1.bookstore.repository;

import com.group1.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	User findByEmail (String email);
}
