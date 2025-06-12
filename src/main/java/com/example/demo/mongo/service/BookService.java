package com.example.demo.mongo.service;

import com.example.demo.mongo.exception.BookException;
import com.example.demo.mongo.model.Book;
import com.example.demo.mongo.repository.BookRepo;
import com.mongodb.client.result.UpdateResult;
import com.sun.tools.jconsole.JConsoleContext;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    MongoTemplate mongoTemplate;



    @Autowired
    private BookRepo bookRepo;


    public ResponseEntity<Book> updateBook(Book book) throws BookException{
        System.out.println("pozvan je servis updateBook...");
        String bookId=book.getId();
        Query query=new Query(Criteria.where("id").is(bookId));
        Update update=new Update();
        update.set("yearPublished",book.getYearPublished());
        update.set("author",book.getAuthor());
        update.set("title",book.getTitle());
        update.set("publisher",book.getPublisher());
        update.set("description",book.getDescription());
        update.set("imageUrl",book.getImageUrl());//
        update.set("isbn",book.getIsbn());

        UpdateResult result=mongoTemplate.updateFirst(query,update,Book.class);
        System.out.println("result: "+result);
        long modifiedDocuments=result.getModifiedCount();
        long foundDocuments= result.getMatchedCount();
        if (foundDocuments==0){
            System.out.println("There is no document ID "+bookId);
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        if(modifiedDocuments==0){
            System.out.println("No documents updated");
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }else{
            System.out.println(result.getModifiedCount());
        }
        System.out.println(result.getModifiedCount()+" document(s) updated.");
        return new ResponseEntity<>(book,HttpStatus.OK);
    }


    public ResponseEntity<List<Book>> getall() throws BookException{
        return new ResponseEntity<>(bookRepo.findAll(Sort.by(Sort.Direction.ASC,"title")), HttpStatus.OK);

    }

    public ResponseEntity<List<Book>> getByAuthor(String author) throws BookException{
        List foundedBooks=bookRepo.findAllByAuthorIgnoreCaseOrderByTitleAsc(author);
        if (foundedBooks.size()==0){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundedBooks,HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> getByTitle(String title)throws BookException{
        List foundedBooks=bookRepo.findAllByTitleIgnoreCaseOrderByTitleAsc(title);
        if (foundedBooks.size()==0){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundedBooks,HttpStatus.OK);
    }

    public ResponseEntity<Book> getByIsbn(String isbn) throws BookException{
        Book foundedBook=bookRepo.findAllByIsbn(isbn);
        if (foundedBook==null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundedBook,HttpStatus.OK);
    }

    public ResponseEntity<Book> createBook(Book book)throws BookException{
        System.out.println("pozvan je servis createBook...");
        Book createdBook=bookRepo.save(book);
        return new ResponseEntity<>(createdBook,HttpStatus.OK);

    }
    public ResponseEntity<String> deleteBook(Book book) throws BookException{
        System.out.println("pozvan je servis deleteBook..");
        String bookId=book.getId();
        if(bookRepo.findById(bookId).isEmpty()==true){
            return new ResponseEntity<>("There is no book ID: "+bookId, HttpStatus.NO_CONTENT);
        }
        bookRepo.deleteById(bookId);
        return new ResponseEntity<>("Book id "+bookId+" deleted.",HttpStatus.OK);
    }


    public ResponseEntity<Optional<Book>> getById(String id) throws BookException {
        Optional<Book> foundedBook=bookRepo.findById(id);
        if (foundedBook.isEmpty() || foundedBook==null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundedBook,HttpStatus.OK);

    }
}
