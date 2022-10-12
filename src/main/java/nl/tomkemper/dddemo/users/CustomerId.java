package nl.tomkemper.dddemo.users;

public class CustomerId {
    private Long id;

    public CustomerId(long id) {
        this.id = id;
    }

    protected CustomerId() {
    }


    public long getValue() {
        return id;
    }

}

