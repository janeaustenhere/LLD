package com.example.meetingscheduler.booking;

import com.example.meetingscheduler.Builder.BookingRequestBuilder;
import com.example.meetingscheduler.model.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
public class MeetingBookingRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String meetingRoomId;
    private List<User> participants;
    private String organizerUserId;

    public MeetingBookingRequest(BookingRequestBuilder bookingRequestBuilder){
        this.startTime = bookingRequestBuilder.startTime;
        this.endTime = bookingRequestBuilder.endTime;
        this.meetingRoomId = bookingRequestBuilder.meetingRoomId;
        this.participants = bookingRequestBuilder.participants;
        this.organizerUserId = bookingRequestBuilder.organizerUserId;

    }

}
