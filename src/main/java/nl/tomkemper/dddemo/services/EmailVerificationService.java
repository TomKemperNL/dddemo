package nl.tomkemper.dddemo.services;

import nl.tomkemper.dddemo.models.Customer;

import java.util.UUID;

public interface EmailVerificationService {
    void sendVerificationEmail(Customer customer);

    void acceptVerificationCode(UUID uid);

}
