package com.group1.bookstore.model;

//import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;
    
    @Column
    private String author;
    
    @Column
    private String description;
    
    @Column
    private String genre;
    
    @Column
    private String coverImageUrl; // URL for the book cover image

    @Column
    private Integer amount;
    
    @Column
    private Integer price;
    
    }
