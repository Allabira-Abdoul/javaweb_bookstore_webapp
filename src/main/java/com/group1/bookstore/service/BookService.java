package com.group1.bookstore.service;

import java.util.List;

import com.group1.bookstore.model.Book;

public interface BookService {
    public Book saveBook(Book book);
    public List<Book> getAllBooks();
    public Book getBookById(Long id);
    public Book getBookByTitle(String title);
    public List<Book> getBookByTitleContaining(String title);
    public List<Book> getBooksByAuthor(String author);
    public List<Book> getBooksByAuthorContaining(String author);
    public List<Book> getBooksByGenre(String genre);
    public List<Book> getBooksByGenreContaining(String genre);
    public Book updateBook(Book book, Long id);
    public void deleteBook(Long id);

}
