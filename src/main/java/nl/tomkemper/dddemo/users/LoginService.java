package nl.tomkemper.dddemo.users;

public interface LoginService {
    Customer getLoggedInCustomer();
    Customer login(EmailAddress email, String password);
}
