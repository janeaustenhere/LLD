package com.example.notificationService.model;

import com.example.notificationService.enums.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class UserPreference {

    private String userId;
    private Set<ChannelType> preferences;
}
