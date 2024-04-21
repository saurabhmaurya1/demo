package com.example.demo.service;

import com.example.demo.entites.Book;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MyService {
    List<Book> getAllBooks();
    Book getBookById(int id);
    Book addBook(Book b);
    void deleteBook(int bId);
    void updateBook(Book b ,int bId);



}
