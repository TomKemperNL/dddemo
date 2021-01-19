package nl.tomkemper.dddemo.services;

import nl.tomkemper.dddemo.models.Order;
import org.springframework.stereotype.Component;

@Component
public class FakeOrderNotificationService implements OrderNotificationService {

    public void sendNotification(Order order) {
        //Tsja... niet ideaal, beter dan de regex dupliceren...
        if (order.getCustomer().getEmailAddress().matches(EmailVerificationService.EMAIL_REGEX)) {
            System.out.println(String.format("Sending order notification for %s to %s",
                    order.getId(),
                    order.getCustomer().getEmailAddress()));
        } else {
            throw new RuntimeException("Invalid EmailAddress");
        }
    }
}
