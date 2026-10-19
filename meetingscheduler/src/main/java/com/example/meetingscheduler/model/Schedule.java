package com.example.meetingscheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Schedule {

    LocalTime startTime;
    LocalTime endTime;
}
