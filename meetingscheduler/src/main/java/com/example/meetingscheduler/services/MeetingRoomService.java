package com.example.meetingscheduler.services;


import com.example.meetingscheduler.model.MeetingRoom;
import com.example.meetingscheduler.repository.IMeetingRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomService {

    private final IMeetingRoomRepository meetingRoomRepository;


    public MeetingRoomService(IMeetingRoomRepository meetingRoomRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
    }


    public void addMeetingRoom(MeetingRoom meetingRoom){
        meetingRoomRepository.addMeetingRoom(meetingRoom);

    }

    public MeetingRoom getMeetingRoomById(String id){
        return meetingRoomRepository.getMeetingRoomById(id).orElse(null);
    }

    public List<MeetingRoom> getAllMeetingRooms(){
        return meetingRoomRepository.getAllMeetingRooms();
    }

    public List<MeetingRoom> getActiveMeetingRooms(){

        return meetingRoomRepository.getActiveMeetingRooms();
    }


}
