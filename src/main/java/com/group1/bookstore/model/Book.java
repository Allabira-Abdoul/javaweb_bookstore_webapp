package com.group1.bookstore.model;

import java.util.List;

//import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;


    @Column
    private String author;

    @Column(columnDefinition = "TEXT")
    private String description;


    @Column
    private String genre;


    @Column
    private String coverImageUrl;

    @Column
    private Integer amount;


    @Column
    private Integer price;

    @Column
    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows;
    
    private Boolean available;
}

