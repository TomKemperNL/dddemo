package nl.tomkemper.dddemo.orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nl.tomkemper.dddemo.books.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private long id;

    private int number;

    @ManyToOne
    private Book book;

    @ManyToOne
    @JsonBackReference
    private Order order;

    protected OrderLine(){
        this.number = 1;
    }

    public OrderLine(Order order, Book book){
        this();
        this.order = order;
        this.book = book;
    }

    public OrderLine(Order order, Book book, int nr){
        this(order, book);
        this.number = nr;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
