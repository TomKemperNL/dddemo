package nl.tomkemper.dddemo.services;

import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.repositories.CustomerRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class FakeEmailVerificationService implements EmailVerificationService {

    private final CustomerRepository customers;

    private Map<UUID, String> lookup = new HashMap<>();

    public FakeEmailVerificationService(CustomerRepository customers){
        this.customers = customers;
    }

    public void sendVerificationEmail(Customer customer){
        UUID verificationCode = UUID.randomUUID();
        this.lookup.put(verificationCode, customer.getEmailAddress());

        if (customer.getEmailAddress().matches(EmailVerificationService.EMAIL_REGEX)) {
            System.out.println("Fake email verification code: " + verificationCode);
        }else{
            throw new RuntimeException("Invalid EmailAddress");
        }
    }

    @Transactional
    public void acceptVerificationCode(UUID uid){
        String email = lookup.get(uid);
        if(email != null){
            Customer c = this.customers.findCustomer(email);
            c.setEmailValidated(true);
        }
    }

}
