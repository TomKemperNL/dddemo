package nl.tomkemper.dddemo.services;

import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.models.EmailAddress;

import javax.servlet.http.HttpSession;

public interface LoginService {
    Customer getLoggedInCustomer();
    Customer login(EmailAddress email, String password);
}
