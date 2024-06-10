package com.group1.bookstore.service;

import java.util.List;

import com.group1.bookstore.model.Borrow;

public interface BorrowService {
    public Borrow saveBorrow(Borrow borrow);
    public List<Borrow> getAllBorrows();
    public Borrow getBorrowById(Long id);
    public List<Borrow> getBorrowsByUserId(Long userId);
    public Borrow updateBorrow(Borrow borrow, Long id);
}
