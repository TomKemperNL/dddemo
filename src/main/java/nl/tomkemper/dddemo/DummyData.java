package nl.tomkemper.dddemo;

import nl.tomkemper.dddemo.models.Author;
import nl.tomkemper.dddemo.models.Book;
import nl.tomkemper.dddemo.models.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class DummyData implements CommandLineRunner {

    private final EntityManager entityManager;

    public DummyData(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Customer c1 = new Customer();
        c1.setEmailAddress("tom@tomkemper.nl");

        entityManager.persist(c1);

        Author jk = new Author();
        jk.setName("JK Rowling");

        entityManager.persist(jk);

        Book harry1 = new Book();
        harry1.setAuthor(jk);
        harry1.setTitle("De Steen der Wijzen");

        entityManager.persist(harry1);

        Book harry2 = new Book();
        harry2.setAuthor(jk);
        harry2.setTitle("De Geheime Kamer");

        entityManager.persist(harry2);

        Author scott = new Author();
        scott.setName("Scott Wlaschin");

        entityManager.persist(scott);

        Book dddFsharp = new Book();
        dddFsharp.setAuthor(scott);
        dddFsharp.setTitle("Domain Modelling Made Functional");

        entityManager.persist(dddFsharp);




    }
}
