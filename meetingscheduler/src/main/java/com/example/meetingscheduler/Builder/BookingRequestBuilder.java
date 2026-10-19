package com.example.meetingscheduler.Builder;


import com.example.meetingscheduler.booking.MeetingBookingRequest;
import com.example.meetingscheduler.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@NoArgsConstructor
public class BookingRequestBuilder {

    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public String meetingRoomId;
    public List<User> participants;
    public String organizerUserId;

    public BookingRequestBuilder startTime(LocalDateTime startTime){
        this.startTime = startTime;
        return this;
    }

    public BookingRequestBuilder endTime(LocalDateTime endTime){
        this.endTime = endTime;
        return this;
    }

    public BookingRequestBuilder meetingRoomId(String meetingRoomId){
        this.meetingRoomId = meetingRoomId;
        return this;
    }

    public BookingRequestBuilder participants(List<User> participants){
        this.participants = participants;
        return this;
    }

    public BookingRequestBuilder organizerUserId(String organizerUserId){
        this.organizerUserId = organizerUserId;
        return this;
    }

    public MeetingBookingRequest build(){
         return new MeetingBookingRequest(this);
    }





}
