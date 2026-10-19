package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.model.MeetingRoom;
import com.example.meetingscheduler.services.MeetingRoomSpecifications;
import jakarta.transaction.Transactional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public class JpaMeetingRoomRepository implements IMeetingRoomRepository{

    private final MeetingRoomJpaRepository repository;
    private final PersistenceMapper mapper;

    public JpaMeetingRoomRepository(MeetingRoomJpaRepository repository, PersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void addMeetingRoom(MeetingRoom room) {

        if(repository.existsById(room.getId())){
            throw new DuplicateKeyException(
                    "Meeting room ID already exists: " + room.getId()
            );
        }
        repository.saveAndFlush(mapper.toEntity(room));

    }

    @Override
    public Optional<MeetingRoom> getMeetingRoomById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<MeetingRoom> getAllMeetingRooms() {
        return repository.findAll(Sort.by("id"))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<MeetingRoom> getActiveMeetingRooms() {
        return repository.findByActiveTrueOrderByIdAsc()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<MeetingRoom> findAvailableRooms(LocalDateTime startTime, LocalDateTime endTime) {
        if(startTime == null  ||  endTime == null || !startTime.isBefore(endTime))
        {
            throw new IllegalArgumentException(
                    "Start time must be before end time");
        }

        return repository.findAll(MeetingRoomSpecifications.availableBetween(
                startTime, endTime), Sort.by("id"))
                .stream()
                .map(mapper:: toDomain)
                .toList();
    }
}
