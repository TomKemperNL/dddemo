package nl.tomkemper.dddemo.services;

import nl.tomkemper.dddemo.models.Customer;

import java.util.UUID;

public interface EmailVerificationService {
    String EMAIL_REGEX = "^(.+)@(.+)$";

    void sendVerificationEmail(Customer customer);

    void acceptVerificationCode(UUID uid);

}
