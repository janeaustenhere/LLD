package com.example.meetingscheduler.repository;


import com.example.meetingscheduler.enums.NotificationType;
import com.example.meetingscheduler.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

    Map<String, User> userMap = new ConcurrentHashMap<>();

    public void addUser(User user){
        userMap.put(user.getUserId(),user);
    }

    public List<User> getAllUsers(){
        return userMap.values().stream().toList();
    }

    public User getUserById(String userId){
        return userMap.get(userId);
    }

    public void updateUserPreference(String userId, NotificationType notificationType){
        userMap.get(userId).setNotificationType(notificationType);

    }

}
