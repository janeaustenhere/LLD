package com.example.meetingscheduler.controller;


import com.example.meetingscheduler.model.MeetingRoom;
import com.example.meetingscheduler.services.MeetingRoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meetingRooms")
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;


    public MeetingRoomController(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addMeetingRoom(
            @Valid @RequestBody MeetingRoom room) {
        meetingRoomService.addMeetingRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Meeting Room added");
    }
}
