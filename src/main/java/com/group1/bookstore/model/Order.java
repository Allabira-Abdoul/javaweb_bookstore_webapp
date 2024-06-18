package com.group1.bookstore.model;





import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


import lombok.Data;


@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // Other order details as needed

    // Constructors, getters, setters
}
