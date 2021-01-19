package nl.tomkemper.dddemo.controllers;

import nl.tomkemper.dddemo.exceptions.NotFoundException;
import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("login")
public class LoginController {
    private static final String CUSTOMER_KEY = "nl.tomkemper.dddemo.customer";
    private final HttpSession session;

    private final CustomerRepository customers;
    public static Customer getLoggedInCustomer(HttpSession session) {
        return (Customer)session.getAttribute(CUSTOMER_KEY);
    }

    public LoginController(CustomerRepository customers, HttpSession session){
        this.customers = customers;
        this.session = session;
    }

    @GetMapping("")
    public Customer getCurrentCustomer(){
        Customer current = getLoggedInCustomer(session);
        if(current == null){
            throw new NotFoundException();
        }

        return current;
    }

    @PostMapping("")
    public void login(@RequestBody Customer customer){
        Customer existing = this.customers.findCustomer(customer.getEmailAddress());

        if(existing == null){
            throw new NotFoundException();
        }

        session.setAttribute(CUSTOMER_KEY, existing);
    }

}
