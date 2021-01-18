package nl.tomkemper.dddemo.controllers;

import nl.tomkemper.dddemo.exceptions.UnauthorizedException;
import nl.tomkemper.dddemo.models.Order;
import nl.tomkemper.dddemo.repositories.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {

    private final OrderRepository orders;

    public OrdersController(OrderRepository orders){
        this.orders = orders;
    }

    @GetMapping("")
    public List<Order> getOrders(){
        if(LoginController.getFakeLoggedInCustomer() == null){
            throw new UnauthorizedException();
        }

        return this.orders.getOrders(LoginController.getFakeLoggedInCustomer());
    }
}
