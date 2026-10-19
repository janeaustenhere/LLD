package com.example.meetingscheduler.factory;


import com.example.meetingscheduler.enums.NotificationType;
import com.example.meetingscheduler.strategy.EmailNotification;
import com.example.meetingscheduler.strategy.NotificationStrategy;
import com.example.meetingscheduler.strategy.SMSNotification;
import com.example.meetingscheduler.strategy.WhatsappNotification;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NotificationFactory {

    private final EmailNotification emailNotification;
    private final SMSNotification smsNotification;
    private final WhatsappNotification whatsappNotification;
    private final Map<NotificationType, NotificationStrategy> notificationTypeNotificationStrategyMap = new HashMap<>();

    public NotificationFactory(EmailNotification emailNotification, SMSNotification smsNotification, WhatsappNotification whatsappNotification) {
        this.emailNotification = emailNotification;
        this.smsNotification = smsNotification;
        this.whatsappNotification = whatsappNotification;
    }

    @PostConstruct
    public void init(){
        this.notificationTypeNotificationStrategyMap.put(NotificationType.EMAIL,emailNotification);
        this.notificationTypeNotificationStrategyMap.put(NotificationType.SMS,smsNotification);
        this.notificationTypeNotificationStrategyMap.put(NotificationType.WHATSAPP, whatsappNotification);

    }


    public NotificationStrategy getNotificationStrategy(NotificationType notificationType) {
        return notificationTypeNotificationStrategyMap.get(notificationType);
    }
}
