package com.example.notificationService.services;


import com.example.notificationService.enums.ChannelType;
import com.example.notificationService.factory.NotificationChannelFactory;
import com.example.notificationService.interfaces.NotificationChannel;
import com.example.notificationService.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationDispatcher {

    private final NotificationChannelFactory notificationChannelFactory;


    public NotificationDispatcher(NotificationChannelFactory notificationChannelFactory) {
        this.notificationChannelFactory = notificationChannelFactory;
    }

    public void dispatch(Notification notification){

        for(ChannelType channelType : notification.getUserPreferences().getPreferences()){

            NotificationChannel notificationChannel = notificationChannelFactory.getNotificationChannel(channelType);
            notificationChannel.send(notification);
        }
    }
}
