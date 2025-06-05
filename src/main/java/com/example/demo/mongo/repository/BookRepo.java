package com.example.demo.mongo.repository;

import com.example.demo.mongo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends MongoRepository<Book, String> {

    List<Book> findAllByAuthorOrderByTitleAsc(String author);

    List<Book> findAllByTitleOrderByTitleAsc(String title);

    Book findAllByIsbn(String isbn);
}
