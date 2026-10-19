package com.example.meetingscheduler.entities;

import com.example.meetingscheduler.enums.BookingStatus;
import com.example.meetingscheduler.model.MeetingRoom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meeting_booking", schema = "scheduler")
@Getter
@Setter
@NoArgsConstructor
public class MeetingBookingEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "booking_id", columnDefinition = "text")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private MeetingRoomEntity room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organizer_user_id", nullable = false)
    private UserEntity organizer;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;


    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookingStatus status;


    @ManyToMany
    @JoinTable(name = "booking_participant",
    schema = "scheduler",
    joinColumns = @JoinColumn(name = "booking_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> participants = new HashSet<>();
}
