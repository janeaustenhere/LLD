package com.example.meetingscheduler.services;


import com.example.meetingscheduler.factory.NotificationFactory;
import com.example.meetingscheduler.model.NotificationMessage;
import com.example.meetingscheduler.model.User;
import com.example.meetingscheduler.strategy.NotificationStrategy;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

   private final NotificationFactory notificationFactory;


    public NotificationService(NotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    public void sendNotification(User user, NotificationMessage notificationMessage){
        NotificationStrategy notificationStrategy = notificationFactory.getNotificationStrategy(user.getNotificationType());
        notificationStrategy.sendNotification(user,notificationMessage);
    }
}
