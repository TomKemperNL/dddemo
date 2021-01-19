package nl.tomkemper.dddemo.users;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionLoginService implements LoginService {
    private static final String CUSTOMER_KEY = "nl.tomkemper.dddemo.customer";
    private final HttpSession session;
    private final CustomerRepository customers;

    public SessionLoginService(HttpSession session, CustomerRepository customers) {
        this.session = session;
        this.customers = customers;
    }

    public Customer getLoggedInCustomer() {
        Object customerId = session.getAttribute(CUSTOMER_KEY);
        if(customerId == null){
            return null;
        }
        return this.customers.get((long)customerId);
    }

    public Customer login(EmailAddress email, String password) {
        //TODO: iets met dat password:)
        Customer existing = this.customers.findCustomer(email);
        if(existing != null){
            this.session.setAttribute(CUSTOMER_KEY, existing.getId());
        }
        return existing;
    }


}
