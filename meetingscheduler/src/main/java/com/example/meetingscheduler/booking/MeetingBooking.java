package com.example.meetingscheduler.booking;

import com.example.meetingscheduler.enums.BookingStatus;
import com.example.meetingscheduler.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
public class MeetingBooking {

    @Setter
    private String meetingBookingId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String meetingRoomId;
    private List<User> participants;

    @Setter
    private BookingStatus status;
    private String organizerUserId;
    public MeetingBooking(
            LocalDateTime startTime,
            LocalDateTime endTime,
            String meetingRoomId,
            List<User> participants,
            String organizerUserId) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingRoomId = meetingRoomId;
        this.participants = List.copyOf(participants);
        this.organizerUserId = organizerUserId;
        this.status = BookingStatus.CONFIRMED;
    }
}
