package nl.tomkemper.dddemo.controllers;

import nl.tomkemper.dddemo.exceptions.NotFoundException;
import nl.tomkemper.dddemo.exceptions.UnauthorizedException;
import nl.tomkemper.dddemo.models.Book;
import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.models.Order;
import nl.tomkemper.dddemo.models.OrderLine;
import nl.tomkemper.dddemo.repositories.BookRepository;
import nl.tomkemper.dddemo.repositories.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("order")
public class CurrentOrderController {

    private static final String ORDER_KEY = "nl.tomkemper.dddemo.order";
    private final BookRepository books;
    private final OrderRepository orders;
    private final HttpSession session;

    private Order getFromSession(){
         return (Order)session.getAttribute(ORDER_KEY);
    }

    private void setInSession(Order order){
        session.setAttribute(ORDER_KEY, order);
    }

    public CurrentOrderController(BookRepository books, OrderRepository orders, HttpSession session) {
        this.books = books;
        this.orders = orders;
        this.session = session;
    }

    private void initializeOrder() {
        Customer current = LoginController.getLoggedInCustomer(session);
        if (current == null) {
            throw new UnauthorizedException();
        }

        Order currentOrder = new Order();
        currentOrder.setCustomer(current);
        setInSession(currentOrder);
    }

    @GetMapping("")
    public Order getCurrentOrder() {
        Order currentOrder = getFromSession();
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

        Order currentOrder = getFromSession();
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
        Order currentOrder = getFromSession();
        if (currentOrder == null) {
            throw new NotFoundException();
        }

        if (LoginController.getLoggedInCustomer(this.session) == null) {
            throw new UnauthorizedException();
        }

        Order finishedOrder = currentOrder;
        setInSession(null);
        this.orders.save(finishedOrder);

        return finishedOrder;
    }

}
