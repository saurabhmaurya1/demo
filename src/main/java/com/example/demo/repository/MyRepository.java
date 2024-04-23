package com.example.demo.repository;

import com.example.demo.entites.Book;
import org.springframework.data.repository.CrudRepository;

public interface MyRepository extends CrudRepository<Book,Integer> {

    public Book findById(int id);
}
