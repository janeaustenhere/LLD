package com.example.notificationService.controller;


import com.example.notificationService.model.UserPreference;
import com.example.notificationService.services.UserPreferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userprefrences")
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;


    public UserPreferenceController(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserPreference(@RequestBody UserPreference userPreference){

        return null;
    }
}
