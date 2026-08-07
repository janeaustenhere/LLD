package com.example.notificationService.services;


import com.example.notificationService.model.Notification;
import com.example.notificationService.model.UserPreference;
import com.example.notificationService.repositories.UserPreferenceRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceService {

    private final UserPreferenceRepository userPreferenceRepository;

    public UserPreferenceService(UserPreferenceRepository userPreferenceRepository) {
        this.userPreferenceRepository = userPreferenceRepository;
    }

    public void addUserPreferences(UserPreference userPreference){
        userPreferenceRepository.addUserPreferences(userPreference);

    }

    public UserPreference getUserPreferences(String userId){

       return userPreferenceRepository.getUserPreferenes(userId);

    }
}
