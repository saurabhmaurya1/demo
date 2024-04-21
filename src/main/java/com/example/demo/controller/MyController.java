package com.example.demo.controller;

import com.example.demo.entites.Book;
import com.example.demo.service.MyService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Optional;

@RestController
public class MyController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @Autowired
    private MyService myService;


    @GetMapping("/books")
    public ResponseEntity<List<Book>> allbooks(){
        List<Book> list = myService.getAllBooks();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

//    getting book by id
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> bookById(@PathVariable("id") int id){
        Book book = myService.getBookById(id);
        if (book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b =null;
        try{
            b=myService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId){
        try {
            myService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //Update Handler
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book ,@PathVariable("bookId") int bookId){
        try {
            myService.updateBook(book,bookId);
            return ResponseEntity.ok().body(book);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }
}
