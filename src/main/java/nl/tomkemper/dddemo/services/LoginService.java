package nl.tomkemper.dddemo.services;

import nl.tomkemper.dddemo.models.Customer;

import javax.servlet.http.HttpSession;

public interface LoginService {
    Customer getLoggedInCustomer();
    Customer login(String email, String password);
}
