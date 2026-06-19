package com.example.bookmyshow.repository;

import com.example.bookmyshow.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepository {

    Map<String, User> userMap = new HashMap<>();

    public User addUser(User user){
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        userMap.put(userId,user);
        return user;
    }

    public User getUserById(String userId){
        return userMap.get(userId);
    }

    public List<User> getAllUsers(){
        return userMap.values().stream().toList();
    }
}
