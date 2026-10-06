package com.example.splitwise.services;

import com.example.splitwise.models.User;
import com.example.splitwise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.addUser(user);
    }

    public List<User> getUserList(){
       return userRepository.getUserList();
    }
}
