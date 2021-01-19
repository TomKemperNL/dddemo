package nl.tomkemper.dddemo.orders;

public interface OrderNotificationService {
    void sendNotification(Order order);
}
