package nl.tomkemper.dddemo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    protected Author() {
    }

    public Author(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void addBook(String title) {
        this.books.add(new Book(this, title));
    }
}
