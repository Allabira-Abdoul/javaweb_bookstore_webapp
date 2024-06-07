package com.group1.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group1.bookstore.model.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    
}
