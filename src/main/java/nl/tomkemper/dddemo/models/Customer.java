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

    public long getId() {
        return id;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
