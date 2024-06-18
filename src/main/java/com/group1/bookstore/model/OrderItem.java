package com.group1.bookstore.model;


import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;  // Price per unit

    // Additional fields, getters, and setters as needed

    public OrderItem() {
        // Default constructor
    }

    public OrderItem(Order order, Book book, Integer quantity, Double price) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }
}

