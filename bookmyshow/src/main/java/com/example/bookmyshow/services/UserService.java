package com.example.bookmyshow.services;


import com.example.bookmyshow.model.User;
import com.example.bookmyshow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
       return userRepository.addUser(user);
    }

    public User getUserById(String userId){
        return userRepository.getUserById(userId);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }


}
