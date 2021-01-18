package nl.tomkemper.dddemo.repositories;

import nl.tomkemper.dddemo.models.Book;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Primary
@Component
public class JpaBookRepository implements BookRepository {

    private final EntityManager entities;

    public JpaBookRepository(EntityManager entities){
        this.entities = entities;
    }

    public List<Book> getBooks(){
        return entities.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public Book findBook(long id) {
        return entities.find(Book.class, id);
    }
}
