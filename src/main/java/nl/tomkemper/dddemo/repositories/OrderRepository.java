package nl.tomkemper.dddemo.repositories;

import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.models.Order;

import java.util.List;

public interface OrderRepository {
    void save(Order finishedOrder);

    List<Order> getOrders(Customer customer);
}
