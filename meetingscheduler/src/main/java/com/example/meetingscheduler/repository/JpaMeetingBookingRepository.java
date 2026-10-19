package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.booking.MeetingBooking;
import com.example.meetingscheduler.entities.MeetingBookingEntity;
import com.example.meetingscheduler.entities.UserEntity;
import com.example.meetingscheduler.enums.BookingStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class JpaMeetingBookingRepository implements IMeetingBookingRepository{

    private final MeetingBookingJpaRepository bookings;
    private final MeetingRoomJpaRepository rooms;
    private final UserJpaRepository users;
    private final PersistenceMapper mapper;

    public JpaMeetingBookingRepository(MeetingBookingJpaRepository bookings, MeetingRoomJpaRepository rooms, UserJpaRepository users, PersistenceMapper mapper) {
        this.bookings = bookings;
        this.rooms = rooms;
        this.users = users;
        this.mapper = mapper;
    }

    @Override
    public MeetingBooking addMeetingSchedule(MeetingBooking booking) {
        MeetingBookingEntity entity = new MeetingBookingEntity();

        entity.setRoom(rooms.getReferenceById(booking.getMeetingRoomId()));
        entity.setOrganizer(users.getReferenceById(booking.getOrganizerUserId()));
        entity.setStartTime(booking.getStartTime());
        entity.setEndTime(booking.getEndTime());
        entity.setStatus(booking.getStatus());

        Set<UserEntity> participants = booking.getParticipants().stream()
                .map(user -> users.getReferenceById(user.getUserId()))
                .collect(Collectors.toSet());

        entity.setParticipants(participants);

        MeetingBookingEntity saved = bookings.saveAndFlush(entity);
        return mapper.toDomain(saved);
    }

    @Override
    @Transactional
    public Optional<MeetingBooking> getMeetingBookingById(String bookingId) {
        return bookings.findOneById(bookingId)
                .map(mapper:: toDomain);
    }

    @Override
    public boolean cancelBooking(String bookingId) {
        Optional<MeetingBookingEntity> result = bookings.findById(bookingId);
        if(result.isEmpty()){
            return false;
        }

        result.get().setStatus(BookingStatus.CANCELLED);
        return true;
    }
}
