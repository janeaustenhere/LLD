package com.example.meetingscheduler.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MeetingRoom {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @Positive
    private int capacity;

    private boolean active = true;


}
