package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.booking.MeetingBooking;

import java.util.Optional;

public interface IMeetingBookingRepository {
    MeetingBooking addMeetingSchedule(MeetingBooking booking);

    Optional<MeetingBooking> getMeetingBookingById(String bookingId);

    boolean cancelBooking(String bookingId);
}