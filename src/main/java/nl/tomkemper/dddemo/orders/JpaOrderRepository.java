package nl.tomkemper.dddemo.orders;

import nl.tomkemper.dddemo.users.Customer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Primary
@Component
public class JpaOrderRepository implements OrderRepository {
    private EntityManager entities;

    public JpaOrderRepository(EntityManager entities) {
        this.entities = entities;
    }

    @Override
    public void save(Order finishedOrder) {
        this.entities.persist(finishedOrder);
    }

    @Override
    public List<Order> getOrders(Customer customer) {
        TypedQuery<Order> query = entities.createQuery("select o from Order o where o.customer = ?1", Order.class);
        query.setParameter(1, customer);

        return query.getResultList();
    }
}
