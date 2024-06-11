package com.group1.bookstore.service;

import java.util.List;

import com.group1.bookstore.model.Borrow;
import com.group1.bookstore.model.User;

public interface BorrowService {
    public Borrow saveBorrow(Borrow borrow);
    public List<Borrow> getAllBorrows();
    public Borrow getBorrowById(Long id);
    public List<Borrow> getBorrowsByUser(User user);
    public Borrow updateBorrow(Borrow borrow, Long id);
}
