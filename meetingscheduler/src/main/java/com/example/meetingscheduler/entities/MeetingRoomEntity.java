package com.example.meetingscheduler.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meeting_room", schema = "scheduler")
@Getter
@Setter
@NoArgsConstructor
public class MeetingRoomEntity {

    @Id
    @Column(name = "room_id", columnDefinition = "text")
    private String id;

    @Column(nullable = false, columnDefinition = "text")
    private String name;

    @Column(nullable = false, columnDefinition = "text")
    private String location;

    @Column(nullable = false)
    private int capacity;

    @Column(name = "is_active", nullable = false)
    private boolean active;
}
