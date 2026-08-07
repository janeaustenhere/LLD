package com.example.notificationService.channel;

import com.example.notificationService.interfaces.NotificationChannel;
import com.example.notificationService.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationService implements NotificationChannel {
    @Override
    public void send(Notification notification) {

        System.out.println("Notification Email is being sent for user : "
                +  notification.getUserId() + " details: " + notification.getMessage());

    }
}
