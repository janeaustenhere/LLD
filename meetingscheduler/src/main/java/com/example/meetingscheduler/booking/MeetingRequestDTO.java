package com.example.meetingscheduler.booking;

import com.example.meetingscheduler.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MeetingRequestDTO {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime endTime;

    @NotBlank
    private String meetingRoomId;

    @NotNull
    private List<@NotBlank String> participantUserIds;

    @NotBlank
    private String organizerUserId;
}
