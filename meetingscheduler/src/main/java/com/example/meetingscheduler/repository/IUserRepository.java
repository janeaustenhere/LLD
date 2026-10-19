package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.enums.NotificationType;
import com.example.meetingscheduler.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    void addUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(String userId);
    boolean updateUserPreference(
            String userId, NotificationType notificationType);
}
