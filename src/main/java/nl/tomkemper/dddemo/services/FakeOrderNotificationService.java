package nl.tomkemper.dddemo.services;

import nl.tomkemper.dddemo.models.Order;
import org.springframework.stereotype.Component;

@Component
public class FakeOrderNotificationService implements OrderNotificationService {

    public void sendNotification(Order order) {
        System.out.println(String.format("Sending order notification for %s to %s",
                order.getId(),
                order.getCustomer().getEmailAddress()));
    }
}
