package com.group1.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group1.bookstore.model.Borrow;
import com.group1.bookstore.model.User;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findByUser(User user);
    
}
