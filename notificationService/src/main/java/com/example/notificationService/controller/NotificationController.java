package com.example.notificationService.controller;


import com.example.notificationService.model.Notification;
import com.example.notificationService.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    final private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotifications(@RequestBody Notification notification){

        notificationService.sendNotification(notification);

        return ResponseEntity.status(HttpStatus.OK).body("Notification Sent");

    }


}
