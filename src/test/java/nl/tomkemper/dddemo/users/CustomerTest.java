package nl.tomkemper.dddemo.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerTest {

    @Autowired
    private EntityManager entities;

    @Test
    public void canPersistCustomer(){
        Customer c = new Customer(EmailAddress.parse("tom.kemper@hu.nl").orElseThrow());
        entities.persist(c);
        entities.flush();
        entities.clear();

        Customer found = entities.find(Customer.class, c.getId());

        assertEquals(c, found);
    }

}
