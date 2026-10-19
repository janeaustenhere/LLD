package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.entities.MeetingRoomEntity;
import com.example.meetingscheduler.model.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MeetingRoomJpaRepository extends
        JpaRepository<MeetingRoomEntity,String>, JpaSpecificationExecutor<MeetingRoomEntity> {

     List<MeetingRoomEntity> findByActiveTrueOrderByIdAsc();
}
