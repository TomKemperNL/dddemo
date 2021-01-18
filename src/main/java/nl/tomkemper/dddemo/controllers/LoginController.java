package nl.tomkemper.dddemo.controllers;

import nl.tomkemper.dddemo.exceptions.NotFoundException;
import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("login")
public class LoginController {
    private final CustomerRepository customers;

    //DO NOT TRY THIS AT HOME
    //This is very much a fake login-controller
    private static Customer fakeLoggedInCustomer;

    public LoginController(CustomerRepository customers){
        this.customers = customers;
    }

    @GetMapping("")
    public Customer getCurrentCustomer(){
        if(fakeLoggedInCustomer == null){
            throw new NotFoundException();
        }

        return fakeLoggedInCustomer;
    }

    @PostMapping("")
    public void login(@RequestBody Customer customer){
        Customer existing = this.customers.findCustomer(customer.getEmailAddress());

        if(existing == null){
            throw new NotFoundException();
        }

        fakeLoggedInCustomer = existing;
    }

    public static Customer getFakeLoggedInCustomer() {
        return fakeLoggedInCustomer;
    }
}
