package com.example.meetingscheduler.entities;

import com.example.meetingscheduler.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_user", schema = "scheduler")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "user_id", columnDefinition = "text")
    private String userId;

    @Column(name = "user_name", nullable = false, columnDefinition = "text")
    private String userName;

    @Column(nullable = false, columnDefinition = "text")
    private String email;

    @Column(name = "phone_number", columnDefinition = "text")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false, length = 20)
    private NotificationType notificationType;
}
