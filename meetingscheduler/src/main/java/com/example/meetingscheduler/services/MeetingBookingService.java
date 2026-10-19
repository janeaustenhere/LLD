package com.example.meetingscheduler.services;


import com.example.meetingscheduler.booking.MeetingBooking;
import com.example.meetingscheduler.booking.MeetingRequestDTO;
import com.example.meetingscheduler.exception.BookingConflictException;
import com.example.meetingscheduler.exception.ResourceNotFoundException;
import com.example.meetingscheduler.factory.NotificationFactory;
import com.example.meetingscheduler.model.BookingCreatedEvent;
import com.example.meetingscheduler.model.MeetingRoom;
import com.example.meetingscheduler.model.NotificationMessage;
import com.example.meetingscheduler.model.User;
import com.example.meetingscheduler.repository.IMeetingBookingRepository;
import com.example.meetingscheduler.repository.IMeetingRoomRepository;
import com.example.meetingscheduler.repository.IUserRepository;
import com.example.meetingscheduler.repository.MeetingBookingRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class MeetingBookingService {

    private final IMeetingRoomRepository rooms;
    private final IUserRepository users;
    private final IMeetingBookingRepository bookings;
    private final ApplicationEventPublisher events;

    public MeetingBookingService(
            IMeetingRoomRepository rooms,
            IUserRepository users,
            IMeetingBookingRepository bookings,
            ApplicationEventPublisher events) {
        this.rooms = rooms;
        this.users = users;
        this.bookings = bookings;
        this.events = events;
    }

    @Transactional
    public MeetingBooking createBookingRequest(MeetingRequestDTO request) {
        if (request.getStartTime() == null
                || request.getEndTime() == null
                || !request.getStartTime().isBefore(request.getEndTime())) {
            throw new IllegalArgumentException(
                    "Start time must be before end time");
        }

        MeetingRoom room = rooms.getMeetingRoomById(
                        request.getMeetingRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found"));

        if (!room.isActive()) {
            throw new IllegalArgumentException("Room is inactive");
        }

        User organizer = requireUser(request.getOrganizerUserId());

        List<String> requestedIds = request.getParticipantUserIds();
        if (requestedIds == null) {
            throw new IllegalArgumentException("Participant IDs are required");
        }

        Set<String> attendeeIds = new LinkedHashSet<>(requestedIds);
        if (attendeeIds.size() != requestedIds.size()) {
            throw new IllegalArgumentException("Duplicate participant IDs");
        }

        // Policy: the organizer attends and counts toward room capacity.
        attendeeIds.add(organizer.getUserId());

        if (attendeeIds.size() > room.getCapacity()) {
            throw new IllegalArgumentException("Room capacity exceeded");
        }

        List<User> attendees = attendeeIds.stream()
                .map(this::requireUser)
                .toList();

        MeetingBooking booking = new MeetingBooking(
                request.getStartTime(),
                request.getEndTime(),
                room.getId(),
                attendees,
                organizer.getUserId());

        try {
            booking = bookings.addMeetingSchedule(booking);
        } catch (DataIntegrityViolationException ex) {
            if (hasSqlState(ex, "23P01")) {
                throw new BookingConflictException(
                        "Room is already booked for this time", ex);
            }
            throw ex;
        }

        events.publishEvent(new BookingCreatedEvent(booking));
        return booking;
    }

    @Transactional(readOnly = true)
    public MeetingBooking getBooking(String bookingId) {
        return bookings.getMeetingBookingById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found"));
    }

    @Transactional
    public void cancelBooking(String bookingId) {
        if (!bookings.cancelBooking(bookingId)) {
            throw new ResourceNotFoundException("Booking not found");
        }
    }

    private User requireUser(String userId) {
        return users.getUserById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found: " + userId));
    }

    private static boolean hasSqlState(Throwable error, String state) {
        for (Throwable cause = error;
             cause != null;
             cause = cause.getCause()) {
            if (cause instanceof SQLException sql
                    && state.equals(sql.getSQLState())) {
                return true;
            }
        }
        return false;
    }
}
