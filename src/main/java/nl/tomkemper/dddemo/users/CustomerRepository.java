package nl.tomkemper.dddemo.users;

import java.util.Optional;

public interface CustomerRepository {
    Customer get(long id);
    Customer findCustomer(EmailAddress emailAddress);

    Optional<Customer> get(CustomerId customerId);
}
