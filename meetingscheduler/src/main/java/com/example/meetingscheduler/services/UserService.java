package com.example.meetingscheduler.services;


import com.example.meetingscheduler.enums.NotificationType;
import com.example.meetingscheduler.exception.ResourceNotFoundException;
import com.example.meetingscheduler.model.User;
import com.example.meetingscheduler.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository userRepository;


    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.addUser(user);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User getUserDetails(String userId) {
        return userRepository.getUserById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + userId));
    }

    public void updateUserPreference(
            String userId, NotificationType notificationType) {

        if (!userRepository.updateUserPreference(userId, notificationType)) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
    }
}
