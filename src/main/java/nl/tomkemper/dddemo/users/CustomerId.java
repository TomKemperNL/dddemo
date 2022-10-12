package nl.tomkemper.dddemo.users;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CustomerId implements Serializable {
    private Long id;
    public CustomerId(long id) {
        this.id = id;
    }
    protected CustomerId() {
    }


    public long getId() {
        return id;
    }
}
