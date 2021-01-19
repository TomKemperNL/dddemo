package nl.tomkemper.dddemo.orders;

import nl.tomkemper.dddemo.UnauthorizedException;
import nl.tomkemper.dddemo.users.Customer;
import nl.tomkemper.dddemo.users.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {

    private final OrderRepository orders;
    private final LoginService login;

    public OrdersController(OrderRepository orders, LoginService login) {
        this.orders = orders;
        this.login = login;
    }

    @GetMapping("")
    public List<Order> getOrders() {
        Customer current = login.getLoggedInCustomer();
        if (current == null) {
            throw new UnauthorizedException();
        }

        return this.orders.getOrders(current);
    }
}
