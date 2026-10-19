package com.example.meetingscheduler.model;


import com.example.meetingscheduler.enums.NotificationType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {

    @NotBlank
    private String userId;

    @NotBlank
    private String userName;

    @NotBlank
    @Email
    private String email;

    private String phoneNumber;

    @NotNull
    private NotificationType notificationType;

}
