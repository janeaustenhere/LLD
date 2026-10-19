package com.example.meetingscheduler.repository;


import com.example.meetingscheduler.booking.MeetingBooking;
import com.example.meetingscheduler.enums.BookingStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MeetingBookingRepository {

    private Map<String, MeetingBooking> meetingBookingMap = new ConcurrentHashMap<>();

    public MeetingBooking addMeetingSchedule(MeetingBooking meetingBooking){
        String meetingBookingId = UUID.randomUUID().toString();
        meetingBooking.setMeetingBookingId(meetingBookingId);
        meetingBookingMap.put(UUID.randomUUID().toString(),meetingBooking);
        return meetingBooking;
    }

    public MeetingBooking updateMeetingBookingStatus(String meetingBookingId, BookingStatus status){
        MeetingBooking meetingBooking = meetingBookingMap.get(meetingBookingId);
        meetingBooking.setStatus(status);
        return meetingBooking;
    }

    public MeetingBooking getMeetingBooklingById(String meetingBooingId){
        return meetingBookingMap.get(meetingBooingId);
    }

    public List<MeetingBooking> getMeetingBookingByStatus(BookingStatus status){

        return meetingBookingMap.values().stream().
                filter(meetingBooking -> meetingBooking.getStatus().equals(status)).toList();
    }
}


