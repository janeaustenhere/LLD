package com.example.notificationService.interfaces;

import com.example.notificationService.model.Notification;

public interface NotificationChannel {

    void send(Notification notification);
}
