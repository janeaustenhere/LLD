package com.example.notificationService.channel;

import com.example.notificationService.interfaces.NotificationChannel;
import com.example.notificationService.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {

        System.out.println("Push Notification being sent to user : " + notification.getUserId()
        + " details: " + notification.getMessage());

    }
}
