package nl.tomkemper.dddemo.users;

public interface CustomerRepository {
    Customer get(long id);
    Customer findCustomer(EmailAddress emailAddress);
}
