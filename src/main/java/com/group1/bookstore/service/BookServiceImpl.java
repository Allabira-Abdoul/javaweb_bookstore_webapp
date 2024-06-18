package com.group1.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.bookstore.model.Book;
import com.group1.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
        private BookRepository bookRepository;

        public BookServiceImpl(BookRepository bookRepository) {
            super();
            this.bookRepository = bookRepository;
        }

        @Override
        public List<Book> getAllBooks() {
            return bookRepository.findAll();
        }

        @Override
        public Book saveBook(Book book) {
            return bookRepository.save(book);
        }

        @Override
        public Book getBookById(Long id) {
            return bookRepository.findById(id).get();
        }

        @Override
        public Book updateBook(Book book) {
            return bookRepository.save(book);
        }

        @Override
        public void deleteBook(Long id) {
            bookRepository.deleteById(id);
        }


        @Override
        public Book getBookByTitle(String title) {
            return bookRepository.findByTitle(title);
        }

        @Override
        public List<Book> getBookByTitleContaining(String title) {
            return bookRepository.findByTitleContaining(title);
        }

        @Override
        public List<Book> getBooksByAuthor(String author) {
            return bookRepository.findByAuthor(author);
        }

        @Override
        public List<Book> getBooksByAuthorContaining(String author) {
            return bookRepository.findByAuthorContaining(author);
        }

        @Override
        public List<Book> getBooksByGenre(String genre) {
            return bookRepository.findByGenre(genre);
        }
    @Override
    public List<Book> getLastTwoBooks() {
        return bookRepository.findLastTwoBooks();
    }

}
