package com.example.notificationService.factory;


import com.example.notificationService.channel.EmailNotificationService;
import com.example.notificationService.channel.PushNotificationChannel;
import com.example.notificationService.channel.SMSNotificationChannel;
import com.example.notificationService.enums.ChannelType;
import com.example.notificationService.interfaces.NotificationChannel;
import com.example.notificationService.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationChannelFactory {

    public NotificationChannel getNotificationChannel(ChannelType channelType){

       return switch (channelType){

            case SMS -> new SMSNotificationChannel();

            case EMAIL -> new EmailNotificationService();

            case PUSH -> new PushNotificationChannel();

            };

    }

}
