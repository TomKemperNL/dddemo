package nl.tomkemper.dddemo;

import nl.tomkemper.dddemo.models.Author;
import nl.tomkemper.dddemo.models.Book;
import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.models.EmailAddress;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class DummyData implements CommandLineRunner {

    private final EntityManager entityManager;

    public DummyData(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        EmailAddress address = EmailAddress.parse("tom@tomkemper.nl").get();

        Customer c1 = new Customer(address);
        entityManager.persist(c1);

        Author jk = new Author("JK Rowling");
        jk.addBook("De Steen der Wijzen");
        jk.addBook("De Geheime Kamer");
        entityManager.persist(jk);

        Author scott = new Author("Scott Wlaschin");
        scott.addBook("Domain Modeling Made Functional");
        entityManager.persist(scott);



    }
}
