package com.example.uber.controller;

import com.example.uber.model.Rider;
import com.example.uber.model.User;
import com.example.uber.model.UserInput;
import com.example.uber.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody UserInput userInput){

        Rider user = null;
        try {
            user = userService.addUser(userInput);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(user);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        Rider user = null;
        try {
            user = userService.getUserById(userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<Rider>> getALLUsers(){
        List<Rider> riderList = new ArrayList<>();
        try {
            riderList = userService.getAllUsers();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(riderList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(riderList);
    }
}
