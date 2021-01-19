package nl.tomkemper.dddemo.services;

import nl.tomkemper.dddemo.models.Order;

public interface OrderNotificationService {
    void sendNotification(Order order);
}
