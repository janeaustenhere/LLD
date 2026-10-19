package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.model.MeetingRoom;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IMeetingRoomRepository {
    void addMeetingRoom(MeetingRoom room);

    Optional<MeetingRoom> getMeetingRoomById(String id);

    List<MeetingRoom> getAllMeetingRooms();

    List<MeetingRoom> getActiveMeetingRooms();

    List<MeetingRoom> findAvailableRooms(
            LocalDateTime startTime, LocalDateTime endTime);
}
