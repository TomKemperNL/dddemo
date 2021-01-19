package nl.tomkemper.dddemo.users;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class FakeEmailVerificationService implements EmailVerificationService {

    private final CustomerRepository customers;

    private Map<UUID, EmailAddress> lookup = new HashMap<>();

    public FakeEmailVerificationService(CustomerRepository customers){
        this.customers = customers;
    }

    public void sendVerificationEmail(Customer customer){
        UUID verificationCode = UUID.randomUUID();
        this.lookup.put(verificationCode, customer.getEmailAddress());
        System.out.println("Fake email verification code: " + verificationCode);
    }

    @Transactional
    public void acceptVerificationCode(UUID uid){
        EmailAddress email = lookup.get(uid);
        if(email != null){
            System.out.println("Verifying email for: " + email);
            Customer c = this.customers.findCustomer(email);
            c.setEmailValidated(true);
        }else{
            System.out.println("No pending request found for: " + uid);
        }
    }

}
