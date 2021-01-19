package nl.tomkemper.dddemo.controllers;

import nl.tomkemper.dddemo.exceptions.UnauthorizedException;
import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.models.Order;
import nl.tomkemper.dddemo.repositories.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {

    private final OrderRepository orders;
    private final HttpSession session;

    public OrdersController(OrderRepository orders, HttpSession session) {
        this.orders = orders;
        this.session = session;
    }

    @GetMapping("")
    public List<Order> getOrders(HttpSession session) {
        Customer current = LoginController.getLoggedInCustomer(session);
        if (current == null) {
            throw new UnauthorizedException();
        }

        return this.orders.getOrders(current);
    }
}
