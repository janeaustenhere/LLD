package com.example.meetingscheduler.repository;


import com.example.meetingscheduler.booking.MeetingBooking;
import com.example.meetingscheduler.entities.MeetingBookingEntity;
import com.example.meetingscheduler.entities.MeetingRoomEntity;
import com.example.meetingscheduler.entities.UserEntity;
import com.example.meetingscheduler.model.MeetingRoom;
import com.example.meetingscheduler.model.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class PersistenceMapper {

    public User toDomain(UserEntity entity) {
        User user = new User();
        user.setUserId(entity.getUserId());
        user.setUserName(entity.getUserName());
        user.setEmail(entity.getEmail());
        user.setPhoneNumber(entity.getPhoneNumber());
        user.setNotificationType(entity.getNotificationType());
        return user;
    }

    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUserId(user.getUserId());
        entity.setUserName(user.getUserName());
        entity.setEmail(user.getEmail());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setNotificationType(user.getNotificationType());
        return entity;
    }

    public MeetingRoom toDomain(MeetingRoomEntity entity) {
        MeetingRoom room = new MeetingRoom();
        room.setId(entity.getId());
        room.setName(entity.getName());
        room.setLocation(entity.getLocation());
        room.setCapacity(entity.getCapacity());
        room.setActive(entity.isActive());
        return room;
    }

    public MeetingRoomEntity toEntity(MeetingRoom room) {
        MeetingRoomEntity entity = new MeetingRoomEntity();
        entity.setId(room.getId());
        entity.setName(room.getName());
        entity.setLocation(room.getLocation());
        entity.setCapacity(room.getCapacity());
        entity.setActive(room.isActive());
        return entity;
    }

    public MeetingBooking toDomain(MeetingBookingEntity entity) {
        List<User> participants = entity.getParticipants().stream()
                .map(this::toDomain)
                .sorted(Comparator.comparing(User::getUserId))
                .toList();

        MeetingBooking booking = new MeetingBooking(
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getRoom().getId(),
                participants,
                entity.getOrganizer().getUserId());

        booking.setMeetingBookingId(entity.getId());
        booking.setStatus(entity.getStatus());
        return booking;
    }
}
