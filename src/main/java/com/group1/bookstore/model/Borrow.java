package com.group1.bookstore.model;

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

    private Long bookId;
    private Long userId;
    private String borrowDate;
    private String returnDate;
    private String status;
    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private String bookCoverImageUrl;    
}
