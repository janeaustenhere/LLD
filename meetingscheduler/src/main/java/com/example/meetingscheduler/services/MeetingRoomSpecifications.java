package com.example.meetingscheduler.services;

import com.example.meetingscheduler.entities.MeetingBookingEntity;
import com.example.meetingscheduler.entities.MeetingRoomEntity;
import com.example.meetingscheduler.enums.BookingStatus;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public final class MeetingRoomSpecifications {

    private MeetingRoomSpecifications() {
    }

    public static Specification<MeetingRoomEntity> availableBetween(
            LocalDateTime start, LocalDateTime end) {

        return (room, query, cb) -> {
            Subquery<Integer> overlap = query.subquery(Integer.class);
            Root<MeetingBookingEntity> booking =
                    overlap.from(MeetingBookingEntity.class);

            overlap.select(cb.literal(1)).where(
                    cb.equal(booking.get("room"), room),
                    booking.get("status").in(
                            BookingStatus.REQUESTED,
                            BookingStatus.CONFIRMED),
                    cb.lessThan(
                            booking.<LocalDateTime>get("startTime"), end),
                    cb.greaterThan(
                            booking.<LocalDateTime>get("endTime"), start)
            );

            return cb.and(
                    cb.isTrue(room.get("active")),
                    cb.not(cb.exists(overlap))
            );
        };
    }
}
