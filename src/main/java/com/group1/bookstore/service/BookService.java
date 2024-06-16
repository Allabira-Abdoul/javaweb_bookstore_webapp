package com.group1.bookstore.service;

import java.util.List;

import com.group1.bookstore.model.Book;

public interface BookService {
    List<Book> getAllBooks();

    Book saveBook(Book book);

    Book getBookById(Long id);

   Book updateBook(Book book);

    void deleteBook(Long id);


     Book getBookByTitle(String title);
     List<Book> getBookByTitleContaining(String title);
     List<Book> getBooksByAuthor(String author);
     List<Book> getBooksByAuthorContaining(String author);
     List<Book> getBooksByGenre(String genre);


}
