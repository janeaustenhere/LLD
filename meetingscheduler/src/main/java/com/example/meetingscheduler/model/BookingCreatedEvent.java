package com.example.meetingscheduler.model;

import com.example.meetingscheduler.booking.MeetingBooking;

public record BookingCreatedEvent(MeetingBooking booking) {
}