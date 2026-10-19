package com.example.meetingscheduler.services;

import com.example.meetingscheduler.booking.MeetingBooking;
import com.example.meetingscheduler.model.BookingCreatedEvent;
import com.example.meetingscheduler.model.NotificationMessage;
import com.example.meetingscheduler.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class BookingNotificationListener {

    private final NotificationService notificationService;

    public BookingNotificationListener(
            NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onBookingCreated(BookingCreatedEvent event) {
        MeetingBooking booking = event.booking();

        NotificationMessage message = new NotificationMessage(
                "Meeting scheduled from " + booking.getStartTime()
                        + " to " + booking.getEndTime());

        for (User user : booking.getParticipants()) {
            try {
                notificationService.sendNotification(user, message);
            } catch (RuntimeException ex) {
                log.error(
                        "Notification failed for booking {} and user {}",
                        booking.getMeetingBookingId(), user.getUserId(), ex);
            }
        }
    }
}