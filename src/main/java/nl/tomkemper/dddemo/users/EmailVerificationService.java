package nl.tomkemper.dddemo.users;

import java.util.UUID;

public interface EmailVerificationService {
    void sendVerificationEmail(Customer customer);

    void acceptVerificationCode(UUID uid);

}
