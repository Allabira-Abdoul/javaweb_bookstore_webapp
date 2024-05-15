package com.group1.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group1.bookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenre(String genre);

    List<Book> findByAuthor(String author);

    Book findByTitle(String title);
    
}