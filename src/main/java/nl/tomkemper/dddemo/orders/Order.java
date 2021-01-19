package nl.tomkemper.dddemo.orders;

import nl.tomkemper.dddemo.books.Book;
import nl.tomkemper.dddemo.users.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    public long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    @ManyToOne
    private Customer customer;

    protected Order() {
    }

    public Order(Customer customer) {
        this();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void addBook(Book toOrder, int nr) {
        this.orderLines.add(new OrderLine(this, toOrder, nr));
    }
}
