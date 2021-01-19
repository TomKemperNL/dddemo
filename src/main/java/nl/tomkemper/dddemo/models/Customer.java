package nl.tomkemper.dddemo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
