package com.group1.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.bookstore.model.Borrow;
import com.group1.bookstore.model.User;
import com.group1.bookstore.repository.BorrowRepository;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    public Borrow saveBorrow(Borrow borrow) {
        
        return borrowRepository.save(borrow);
    }

    public Borrow getBorrowById(Long id) {
        return borrowRepository.findById(id).orElse(null);
    }

    public Borrow updateBorrow(Borrow borrow, Long id) {
        Borrow existingBorrow = borrowRepository.findById(id).orElse(null);
        existingBorrow.setBook(borrow.getBook());
        existingBorrow.setUser(borrow.getUser());
        existingBorrow.setBorrowDate(borrow.getBorrowDate());
        existingBorrow.setReturnDate(borrow.getReturnDate());
        existingBorrow.setReturned(borrow.getReturned());

        return borrowRepository.save(existingBorrow);

    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public List<Borrow> getBorrowsByUser(User user) {
        return borrowRepository.findByUser(user);
    }
}
