package com.example.notificationService.services;

import com.example.notificationService.model.Notification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AsyncNotificationService {

    private final NotificationService notificationService;
    private final ExecutorService executorService;


    public AsyncNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public void sendNotification(Notification notification){

        executorService.submit(() -> notificationService.sendNotification(notification));
    }


}
