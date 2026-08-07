package com.example.notificationService.model;

import com.example.notificationService.enums.ChannelType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Set;

@Data
public class Notification {

    private String userId;
    private String message;

    @JsonIgnore
    private UserPreference userPreferences;


}
