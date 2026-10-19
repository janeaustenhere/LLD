package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.entities.MeetingBookingEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingBookingJpaRepository extends
        JpaRepository<MeetingBookingEntity,String> {

    @EntityGraph(attributePaths = {"room", "organizer","participants"})
    Optional<MeetingBookingEntity> findOneById(String Id);
}
