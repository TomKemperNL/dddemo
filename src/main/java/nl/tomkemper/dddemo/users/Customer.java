package nl.tomkemper.dddemo.users;

import nl.tomkemper.dddemo.orders.Order;
import nl.tomkemper.dddemo.orders.OrderNotificationService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Customer {


    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String emailAddress;

    protected Customer() {
    }

    public Customer(EmailAddress address) {
        this();
        this.emailAddress = address.getValue();
    }

    private boolean isEmailValidated;

    public long getId() {
        return id;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress.getValue();
    }

    public EmailAddress getEmailAddress() {
        return EmailAddress.deserialize(this.emailAddress);
    }

    public boolean isEmailValidated() {
        return isEmailValidated;
    }

    public void setEmailValidated(boolean isEmailValidated) {
        this.isEmailValidated = isEmailValidated;
    }

    public Order startOrder(){
        return new Order(this);
    }

    public void completeOrder(Order order, OrderNotificationService service){
        if(this.isEmailValidated){
            service.sendNotification(order);
        }else{
            throw new RuntimeException("Email was not validated");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && emailAddress.equals(customer.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailAddress);
    }
}
