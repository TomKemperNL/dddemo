package nl.tomkemper.dddemo.repositories;

import nl.tomkemper.dddemo.models.Customer;

public interface CustomerRepository {
    Customer findCustomer(String emailAddress);
}
