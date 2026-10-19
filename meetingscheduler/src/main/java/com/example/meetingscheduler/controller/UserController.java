package com.example.meetingscheduler.controller;


import com.example.meetingscheduler.enums.NotificationType;
import com.example.meetingscheduler.model.User;
import com.example.meetingscheduler.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody @Valid User user){

        try {
            userService.addUser(user);
        } catch (Exception e) {
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not created");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = new ArrayList<>();
        try{

            users = userService.getAllUsers();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        if(!users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(users);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(users);
        }
    }

    @GetMapping("/getUserdetails/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable String userId){
        User user = null;
        try{

            user = userService.getUserDetails(userId);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user);
        }

        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        }

            return ResponseEntity.status(HttpStatus.FOUND).body(user);


    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserPreference(@PathVariable String userId,
                                                       @RequestParam NotificationType notificationType){

        try{

            userService.updateUserPreference(userId,notificationType);

        } catch (Exception e) {
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated");
        }

        return ResponseEntity.status(HttpStatus.OK).body("User updated");


    }
}
