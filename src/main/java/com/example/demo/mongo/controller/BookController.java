package com.example.demo.mongo.controller;

import com.example.demo.mongo.model.Book;
import com.example.demo.mongo.service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// koristio sam materija sa https://www.mongodb.com/resources/products/compatibilities/spring-boot#update-using-mongotemplate

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/getall")
    public ResponseEntity<List<Book>> getall(){
        return bookService.getall();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Book>> getById(@PathVariable String id){
        return bookService.getById(id);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getByAuthor(@PathVariable String author){
        System.out.println("pozvan je kontroller getByAuthor...");
        return bookService.getByAuthor(author);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getByTitle(@PathVariable String title){
        System.out.println("pozvan je kontroler getByTitle...");
        return bookService.getByTitle(title);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Optional<Book>> getByIsbn(@PathVariable String isbn){
        System.out.println("pozvan je kontroler getByIsbn");
        return bookService.getByIsbn(isbn);
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        System.out.println("pozvan je kontroler createBook...");
        return bookService.createBook(book);
    }

    @PutMapping("")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        System.out.println("pozvan je kontroler updateBook....");
        return bookService.updateBook(book);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteBook(@RequestBody Book book){
        System.out.println("pozvan je kontroler deleteBook...");
        return bookService.deleteBook(book);
    }
}
