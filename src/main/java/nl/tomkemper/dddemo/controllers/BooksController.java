package nl.tomkemper.dddemo.controllers;

import nl.tomkemper.dddemo.models.Book;
import nl.tomkemper.dddemo.repositories.JpaBookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
public class BooksController {

    private final JpaBookRepository books;

    public BooksController(JpaBookRepository books){
        this.books = books;
    }

    @GetMapping()
    public List<Book> getBooks(){
        return this.books.getBooks();
    }
}
