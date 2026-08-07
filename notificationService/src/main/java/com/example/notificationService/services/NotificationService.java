package com.example.notificationService.services;


import com.example.notificationService.model.Notification;
import com.example.notificationService.model.UserPreference;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final UserPreferenceService userPreferenceService;

    private final NotificationDispatcher notificationDispatcher;


    public NotificationService(UserPreferenceService userPreferenceService, NotificationDispatcher notificationDispatcher) {
        this.userPreferenceService = userPreferenceService;
        this.notificationDispatcher = notificationDispatcher;
    }

    public void sendNotification(Notification notification){

        UserPreference userPreference = userPreferenceService.getUserPreferences(notification.getUserId());
        notification.setUserPreferences(userPreference);
        notificationDispatcher.dispatch(notification);

    }
}
