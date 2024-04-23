package com.example.demo.service;

import com.example.demo.entites.Book;
import com.example.demo.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyServiceImpl implements MyService {

//    private static List<Book> list =new ArrayList<>();
//    static{
//        list.add(new Book(12,"java complete refrence","xyz"));
//        list.add(new Book(22,"python complete refrence","zxy"));
//        list.add(new Book(32,"c++ complete refrence","xydz"));
//
//    }

    @Autowired
    private MyRepository myRepository;

    //Get all books
    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) myRepository.findAll();
    }


    // get book by id
    @Override
    public Book getBookById(int id) {
        Book book =null;
        try {
//            book = list.stream().filter(e -> e.getId() == id).findFirst().get();
            book = (Book) myRepository.findById(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }


    // Adding a book
    @Override
    public Book addBook(Book b) {
//        list.add(b);
        Book result =myRepository.save(b);
        return result;
    }


//    delete a book
    @Override
    public void deleteBook(int bId) {
//        list=list.stream().filter(book -> book.getId()!=bId).collect(Collectors.toList());
        myRepository.deleteById(bId);
    }

    @Override
    public void updateBook(Book book, int bId) {
//        list=list.stream().map(b->{
//            if(b.getId()==bId){
//                b.setTitle(book.getTitle());
//                b.setAuthor(book.getAuthor());
//            }
//            return b;
//        }).collect(Collectors.toList());
        book.setId(bId);
        myRepository.save(book);
    }

}
