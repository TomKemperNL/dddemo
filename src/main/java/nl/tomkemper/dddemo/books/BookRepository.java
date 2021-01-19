package nl.tomkemper.dddemo.books;

import nl.tomkemper.dddemo.books.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getBooks();

    Book findBook(long id);
}
