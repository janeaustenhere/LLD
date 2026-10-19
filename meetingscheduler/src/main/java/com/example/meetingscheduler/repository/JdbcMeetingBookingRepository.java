package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.booking.MeetingBooking;
import com.example.meetingscheduler.enums.BookingStatus;
import com.example.meetingscheduler.enums.NotificationType;
import com.example.meetingscheduler.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class JdbcMeetingBookingRepository implements IMeetingBookingRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMeetingBookingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MeetingBooking addMeetingSchedule(MeetingBooking booking) {
        jdbcTemplate.update("""
                INSERT INTO scheduler.meeting_booking
                (booking_id, room_id, organizer_user_id, start_time,
                end_time, status) VALUES (?,?,?,?,?,?)
                """,
                booking.getMeetingBookingId(),
                booking.getMeetingRoomId(),
                booking.getOrganizerUserId(),
                Timestamp.valueOf(booking.getStartTime()),
                Timestamp.valueOf(booking.getEndTime()),
                booking.getStatus().name());

        jdbcTemplate.batchUpdate("""
                
                INSERT INTO scheduler.booking_participant
                (booking_id, user_id) VALUES (?,?)
                """, booking.getParticipants(),100,
                (ps,user) -> {
                    ps.setString(1, booking.getMeetingBookingId());
                    ps.setString(2, user.getUserId());
                });

        return booking;

    }

    @Override
    public Optional<MeetingBooking> getMeetingBookingById(String bookingId) {

        var rows = jdbcTemplate.query("""
                SELECT booking_id, room_id, organizer_user_id,
                start_time, end_time, status FROM scheduler.meeting_booking
                WHERE booking_id = ?
                """, (rs, rowNum) ->  new BookingRow(
                        rs.getString("booking_id"),
                        rs.getString("room_id"),
                        rs.getString("organizer_user_id"),
                        rs.getObject("start_time", LocalDateTime.class),
                        rs.getObject("end_time", LocalDateTime.class),
                        BookingStatus.valueOf(rs.getString("status"))),
                bookingId);

        if(rows.isEmpty()){
            return Optional.empty();
        }

        BookingRow row = rows.getFirst();
        MeetingBooking booking = new MeetingBooking(
                row.startTime(), row.endTime(), row.roomId(),
                loadParticipants(bookingId), row.organizerId()
        );

        booking.setMeetingBookingId(row.bookingId());
        booking.setStatus(row.status());
        return Optional.of(booking);
    }

    private List<User> loadParticipants(String bookingId) {
        return jdbcTemplate.query("""
                SELECT u.user_id, u.user_name, u.email,
                       u.phone_number, u.notification_type
                FROM scheduler.app_user u
                JOIN scheduler.booking_participant p
                  ON p.user_id = u.user_id
                WHERE p.booking_id = ?
                ORDER BY u.user_id
                """,
                (rs, rowNum) -> {
                    User user = new User();
                    user.setUserId(rs.getString("user_id"));
                    user.setUserName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setNotificationType(NotificationType.valueOf(
                            rs.getString("notification_type")));
                    return user;
                },
                bookingId);
    }


    @Override
    public boolean cancelBooking(String bookingId) {
        return jdbcTemplate.update(
                """
                        UPDATE scheduler.meeting_booking SET status = 'CANCELLED'
                        WHERE booking_id = ?
                        """, bookingId) == 1;

    }

    private record BookingRow(
            String bookingId,
            String roomId,
            String organizerId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            BookingStatus status
    ){}
}
