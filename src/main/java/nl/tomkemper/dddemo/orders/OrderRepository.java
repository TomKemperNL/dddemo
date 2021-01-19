package nl.tomkemper.dddemo.orders;

import nl.tomkemper.dddemo.users.Customer;

import java.util.List;

public interface OrderRepository {
    void save(Order finishedOrder);

    List<Order> getOrders(Customer customer);
}
