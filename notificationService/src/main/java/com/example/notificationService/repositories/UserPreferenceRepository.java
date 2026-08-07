package com.example.notificationService.repositories;


import com.example.notificationService.enums.ChannelType;
import com.example.notificationService.model.UserPreference;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserPreferenceRepository {

    Map<String, UserPreference> map = new ConcurrentHashMap<>();

    public void addUserPreferences(UserPreference userPreference) {

        map.put(userPreference.getUserId(), userPreference);

    }

    public UserPreference getUserPreferenes(String userId) {

        return map.getOrDefault(userId, new UserPreference(userId, Set.of(ChannelType.EMAIL)));
    }
}
