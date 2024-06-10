package com.group1.bookstore.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long bookId;
    
    @Column
    private Long userId;
    
    @Column
    private Date borrowDate;
    
    @Column
    private Date returnDate;
    
    @Column
    private Boolean returned;  
}
