package nl.tomkemper.dddemo.repositories;

import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.models.EmailAddress;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Primary
public class JpaCustomerRepository implements CustomerRepository {

    private final EntityManager entities;

    public JpaCustomerRepository(EntityManager entities) {
        this.entities = entities;
    }

    @Override
    public Customer findCustomer(EmailAddress emailAddress) {
        TypedQuery<Customer> query = entities.createQuery("select c from Customer c where c.emailAddress = ?1", Customer.class);
        query.setParameter(1, emailAddress.getValue());
        List<Customer> results = query.getResultList();

        if (results.size() > 1) {
            throw new RuntimeException(String.format("Duplicate result on a unique column? (email: %s)", emailAddress));
        }

        if(results.size() == 0){
            return null;
        }

        return results.get(0);
    }
}
