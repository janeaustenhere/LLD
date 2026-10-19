package com.example.meetingscheduler.repository;


import com.example.meetingscheduler.model.MeetingRoom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MeetingRoomRepository {

    Map<String, MeetingRoom> meetingRoomMap = new ConcurrentHashMap<>();

    public void addMeetingRoom(MeetingRoom meetingRoom){
        meetingRoomMap.put(meetingRoom.getId(), meetingRoom);

    }

    public MeetingRoom getMeetingRoomById(String id){
        return meetingRoomMap.get(id);
    }

    public List<MeetingRoom> getAllMeetingRooms(){
        return meetingRoomMap.values().stream().toList();
    }

    public List<MeetingRoom> getActiveMeetingRooms(){
        return meetingRoomMap.values().stream().filter(MeetingRoom::isActive).toList();
    }
}
