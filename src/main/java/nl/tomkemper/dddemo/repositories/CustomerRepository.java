package nl.tomkemper.dddemo.repositories;

import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.models.EmailAddress;

public interface CustomerRepository {
    Customer get(long id);
    Customer findCustomer(EmailAddress emailAddress);
}
