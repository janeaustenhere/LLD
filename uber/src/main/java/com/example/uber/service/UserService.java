package com.example.uber.service;


import com.example.uber.factory.UserObjectCreateFactory;
import com.example.uber.model.Driver;
import com.example.uber.model.Rider;
import com.example.uber.model.UserInput;
import com.example.uber.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserObjectCreateFactory userObjectCreateFactory;


    public UserService(UserRepository userRepository, UserObjectCreateFactory userObjectCreateFactory) {
        this.userRepository = userRepository;
        this.userObjectCreateFactory = userObjectCreateFactory;
    }

    public Rider addUser(UserInput userInput){
         Rider user = (Rider) userObjectCreateFactory.createUserObject(userInput);
         userRepository.save(user);
         return user;
    }

    public Rider getUserById(String userId){
        return userRepository.getRiderById(userId);
    }

    public List<Rider> getAllUsers (){
        return userRepository.getAllRiderList();
    }




}
