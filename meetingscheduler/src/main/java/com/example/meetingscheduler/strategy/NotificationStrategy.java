package com.example.meetingscheduler.strategy;

import com.example.meetingscheduler.model.NotificationMessage;
import com.example.meetingscheduler.model.User;

public interface NotificationStrategy {

    void sendNotification(User user, NotificationMessage notificationMessage);
}
