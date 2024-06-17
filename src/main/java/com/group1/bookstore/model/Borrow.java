package com.group1.bookstore.model;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class Borrow {

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
    @FutureOrPresent
    private Date borrowDate;
    
    @Column
    private Date returnDate;
    
    @Column
    private Boolean returned;  
}
