package com.group1.bookstore.service;



import com.group1.bookstore.model.Book;
import com.group1.bookstore.model.Order;
import com.group1.bookstore.model.User;

import java.util.List;

public interface OrderService {

        Order placeOrder(Order order);
        List<Order> getAllOrders();
        Order getOrderById(Long id);
        // Other methods as needed
    }
