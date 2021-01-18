package nl.tomkemper.dddemo.repositories;

import nl.tomkemper.dddemo.models.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getBooks();

    Book findBook(long id);
}
