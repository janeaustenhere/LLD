package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.model.MeetingRoom;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class JdbcMeetingRoomRepository implements IMeetingRoomRepository{

    private final JdbcTemplate jdbcTemplate;

    private static final String SELECT_ROOMS = """
            SELECT room_id, name, location, capacity, is_Active
            FROM scheduler.meeting_room
            """;

    private static final RowMapper<MeetingRoom> ROOM_MAPPER = (rs, rowNum)->{
        MeetingRoom room = new MeetingRoom();
        room.setId(rs.getString("room_id"));
        room.setName(rs.getString("name"));
        room.setCapacity(rs.getInt("capacity"));
        room.setLocation(rs.getString("location"));
        room.setActive(rs.getBoolean("is_active"));
        return room;
    };

    public JdbcMeetingRoomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMeetingRoom(MeetingRoom room) {

        jdbcTemplate.update("""
                INSERT INTO scheduler.meeting_room(room_id, name,
                location, capacity, is_active)
                VALUES (?,?,?,?,?)
                """, room.getId(), room.getName(), room.getLocation(),
                room.getCapacity(), room.isActive());

    }

    @Override
    public Optional<MeetingRoom> getMeetingRoomById(String id) {
        return jdbcTemplate.query(
                SELECT_ROOMS + "WHERE room_id = ?",
                ROOM_MAPPER, id).stream().findFirst();

    }

    @Override
    public List<MeetingRoom> getAllMeetingRooms() {
        return jdbcTemplate.query(SELECT_ROOMS + " ORDER BY room_id" , ROOM_MAPPER);
    }

    @Override
    public List<MeetingRoom> getActiveMeetingRooms() {
        return jdbcTemplate.query(
                SELECT_ROOMS + " WHERE is_Active = true ORDER BY room_id",
                ROOM_MAPPER);

    }

    @Override
    public List<MeetingRoom> findAvailableRooms(LocalDateTime startTime, LocalDateTime endTime) {
        return jdbcTemplate.query("""
                SELECT r.room_id, r.name, r.location,
                r.capacity, r.is_active FROM scheduler.meeting_room r
                WHERE r.is_Active = true
                AND NOT EXISTS ( SELECT 1 FROM scheduler.meeting_booking b
                WHERE b.room_id = r.room_id
                AND b.status IN('REQUESTED', 'CONFIRMED')
                AND b.start_time < ?
                AND b.end_time > ? )
                ORDER BY r.room_id
                """, ROOM_MAPPER, Timestamp.valueOf(endTime),
                Timestamp.valueOf(startTime));
    }
}
