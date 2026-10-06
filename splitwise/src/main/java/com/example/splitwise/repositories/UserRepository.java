package com.example.splitwise.repositories;


import com.example.splitwise.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class UserRepository {

    Map<String, User> userMap = new ConcurrentHashMap<>();

    public void addUser(User user){
        userMap.put(user.getId(),user);
    }

    public List<User> getUserList(){

        return userMap.values().stream().toList();
    }
}
