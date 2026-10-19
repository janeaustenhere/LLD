package com.example.meetingscheduler.controller;


import com.example.meetingscheduler.booking.MeetingBooking;
import com.example.meetingscheduler.booking.MeetingRequestDTO;
import com.example.meetingscheduler.model.MeetingRoom;
import com.example.meetingscheduler.services.MeetingBookingService;
import com.example.meetingscheduler.services.MeetingRoomSchedulerService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meetingBooking")
public class MeetingBookingController {

    private final MeetingBookingService bookingService;
    private final MeetingRoomSchedulerService meetingRoomSchedulerService;

    public MeetingBookingController(MeetingBookingService bookingService, MeetingRoomSchedulerService meetingRoomSchedulerService) {
        this.bookingService = bookingService;
        this.meetingRoomSchedulerService = meetingRoomSchedulerService;
    }

    @GetMapping("/getAvailableRoom")
    public ResponseEntity<List<MeetingRoom>> getAvailableMeetingRoom(
            @RequestParam String startTime,
            @RequestParam String endTime) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");

        LocalDateTime start = LocalDateTime.parse(startTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endTime, formatter);

        return ResponseEntity.ok(
                meetingRoomSchedulerService.getAvailableMeetingRoom(start, end));
    }

    @PostMapping("/book")
    public ResponseEntity<MeetingBooking> bookMeetingRoom(
            @Valid @RequestBody MeetingRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.createBookingRequest(request));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<MeetingBooking> getBooking(
            @PathVariable String bookingId) {
        return ResponseEntity.ok(bookingService.getBooking(bookingId));
    }

    @PatchMapping("/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(
            @PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }


}
