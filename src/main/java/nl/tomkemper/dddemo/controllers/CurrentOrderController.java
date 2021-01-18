package nl.tomkemper.dddemo.controllers;

import nl.tomkemper.dddemo.exceptions.NotFoundException;
import nl.tomkemper.dddemo.exceptions.UnauthorizedException;
import nl.tomkemper.dddemo.models.Book;
import nl.tomkemper.dddemo.models.Order;
import nl.tomkemper.dddemo.models.OrderLine;
import nl.tomkemper.dddemo.repositories.BookRepository;
import nl.tomkemper.dddemo.repositories.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class CurrentOrderController {

    private final BookRepository books;
    private final OrderRepository orders;

    //DO NOT TRY THIS AT HOME
    //This is very much a fake order-controller
    private static Order currentOrder;


    public CurrentOrderController(BookRepository books, OrderRepository orders) {
        this.books = books;
        this.orders = orders;
    }

    private void initializeOrder() {
        if (LoginController.getFakeLoggedInCustomer() == null) {
            throw new UnauthorizedException();
        }
        currentOrder = new Order();
        currentOrder.setCustomer(LoginController.getFakeLoggedInCustomer());
    }

    @GetMapping("")
    public Order getCurrentOrder() {
        if (currentOrder == null) {
            initializeOrder();
        }

        return currentOrder;
    }

    @PostMapping("/books")
    public void addBook(@RequestBody Book b, @RequestParam(required = false, defaultValue = "1") int nr) {
        Book toOrder = this.books.findBook(b.getId());

        if (toOrder == null) {
            throw new NotFoundException();
        }

        if (currentOrder == null) {
            initializeOrder();
        }

        OrderLine line = new OrderLine();
        line.setBook(toOrder);
        line.setNumber(nr);
        line.setOrder(currentOrder);

        currentOrder.getOrderLines().add(line);
    }

    @Transactional
    @PostMapping("/complete")
    public Order finishOrder() {
        if (currentOrder == null) {
            throw new NotFoundException();
        }

        if (LoginController.getFakeLoggedInCustomer() == null) {
            throw new UnauthorizedException();
        }

        Order finishedOrder = currentOrder;
        currentOrder = null;
        this.orders.save(finishedOrder);

        return finishedOrder;
    }

}
