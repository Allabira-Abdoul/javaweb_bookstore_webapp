package com.group1.bookstore.model;

//import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(
    name = "user_id"
  )
  private User user;

  @ManyToOne
  @JoinColumn(
    name = "book_id"
  )
  private Book book;

  @Column
  private Double rating; // Numerical score (e.g., 1-5 stars)
  
  @Column(columnDefinition = "TEXT")
  private String reviewText;

  // Getters and Setters omitted for brevity
}
