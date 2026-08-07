package com.example.notificationService.channel;

import com.example.notificationService.interfaces.NotificationChannel;
import com.example.notificationService.model.Notification;
import org.springframework.stereotype.Component;


@Component
public class SMSNotificationChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {

        System.out.println("Notification SMS being sent to userid: "
                + notification.getMessage() + " details: " + notification.getMessage());

    }
}
