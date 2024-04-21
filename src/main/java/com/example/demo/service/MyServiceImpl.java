package com.example.demo.service;

import com.example.demo.entites.Book;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyServiceImpl implements MyService {

    private static List<Book> list =new ArrayList<>();
    static{
        list.add(new Book(12,"java complete refrence","xyz"));
        list.add(new Book(22,"python complete refrence","zxy"));
        list.add(new Book(32,"c++ complete refrence","xydz"));

    }

    //Get all books
    @Override
    public List<Book> getAllBooks() {
        return list;
    }


    // get book by id
    @Override
    public Book getBookById(int id) {
        Book book =null;
        try {
            book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }


    // Adding a book
    @Override
    public Book addBook(Book b) {
        list.add(b);
        return b;
    }

    @Override
    public void deleteBook(int bId) {
        list=list.stream().filter(book -> book.getId()!=bId).collect(Collectors.toList());
    }

    @Override
    public void updateBook(Book book, int bId) {
        list=list.stream().map(b->{
            if(b.getId()==bId){
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());
    }

}
