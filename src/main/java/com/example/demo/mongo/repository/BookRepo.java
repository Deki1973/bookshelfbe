package com.example.demo.mongo.repository;

import com.example.demo.mongo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends MongoRepository<Book, String> {

    List<Book> findAllByAuthorIgnoreCaseOrderByTitleAsc(String author);

    List<Book> findAllByTitleIgnoreCaseOrderByTitleAsc(String title);

    Optional<Book> findAllByIsbn(String isbn);
}
