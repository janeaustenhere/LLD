package com.example.meetingscheduler.services;

import com.example.meetingscheduler.Builder.BookingRequestBuilder;
import com.example.meetingscheduler.booking.MeetingBookingRequest;
import com.example.meetingscheduler.booking.MeetingRequestDTO;
import com.example.meetingscheduler.model.MeetingRoom;
import com.example.meetingscheduler.model.Schedule;
import com.example.meetingscheduler.repository.IMeetingRoomRepository;
import com.example.meetingscheduler.repository.MeetingRoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MeetingRoomSchedulerService {

    private final IMeetingRoomRepository roomRepository;

    public MeetingRoomSchedulerService(
            IMeetingRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<MeetingRoom> getAvailableMeetingRoom(
            LocalDateTime startTime, LocalDateTime endTime) {

        if (startTime == null || endTime == null
                || !startTime.isBefore(endTime)) {
            throw new IllegalArgumentException(
                    "Start time must be before end time");
        }

        return roomRepository.findAvailableRooms(startTime, endTime);
    }
}

