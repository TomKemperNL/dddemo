package nl.tomkemper.dddemo.orders;

import nl.tomkemper.dddemo.NotFoundException;
import nl.tomkemper.dddemo.UnauthorizedException;
import nl.tomkemper.dddemo.books.Book;
import nl.tomkemper.dddemo.users.Customer;
import nl.tomkemper.dddemo.books.BookRepository;
import nl.tomkemper.dddemo.users.LoginService;
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
    private final LoginService login;
    private final OrderNotificationService notificationService;

    private Order getFromSession() {
        Order currentOrder = (Order) session.getAttribute(ORDER_KEY);
        if(currentOrder != null){
            //Dit is niet zo netjes, want eeeeigenlijk moeten we hier niet een 'echt' order opslaan
            currentOrder.setCustomer(login.getLoggedInCustomer());
        }
        return currentOrder;
    }

    private void setInSession(Order order) {
        session.setAttribute(ORDER_KEY, order);
    }

    public CurrentOrderController(
            BookRepository books,
            OrderRepository orders,
            LoginService login,
            OrderNotificationService notificationService,
            HttpSession session) {
        this.books = books;
        this.orders = orders;
        this.session = session;
        this.login = login;
        this.notificationService = notificationService;
    }

    private Order initializeOrder() {
        Customer current = login.getLoggedInCustomer();
        if (current == null) {
            throw new UnauthorizedException();
        }

        Order currentOrder = current.startOrder();
        setInSession(currentOrder);
        return currentOrder;
    }

    @GetMapping("")
    public Order getCurrentOrder() {
        Order currentOrder = getFromSession();
        if (currentOrder == null) {
            currentOrder = initializeOrder();
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
            currentOrder = initializeOrder();
        }

        currentOrder.addBook(toOrder, nr);
    }

    @Transactional
    @PostMapping("/complete")
    public Order finishOrder() {
        Order finishedOrder = getFromSession();
        Customer currentCustomer = login.getLoggedInCustomer();
        if (finishedOrder == null) {
            throw new NotFoundException();
        }

        if (login.getLoggedInCustomer() == null) {
            throw new UnauthorizedException();
        }

        setInSession(null);
        this.orders.save(finishedOrder);
        currentCustomer.completeOrder(finishedOrder, this.notificationService);
        return finishedOrder;
    }

}
