package com.example.meetingscheduler.strategy;

import com.example.meetingscheduler.model.NotificationMessage;
import com.example.meetingscheduler.model.User;
import org.springframework.stereotype.Component;


@Component
public class WhatsappNotification implements NotificationStrategy{
    @Override
    public void sendNotification(User user, NotificationMessage notificationMessage) {

    }
}
