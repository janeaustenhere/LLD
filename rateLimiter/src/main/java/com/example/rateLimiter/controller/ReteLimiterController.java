package com.example.rateLimiter.controller;


import com.example.rateLimiter.models.User;
import com.example.rateLimiter.services.RateLimiterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ReteLimiterController {

    private  final RateLimiterService rateLimiterService;

    public ReteLimiterController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }


    @GetMapping("/allowedRequest")
    public ResponseEntity<Boolean> checkIfRequestAllowed(User user){

       Boolean isAlowed  = rateLimiterService.allowedRequest(user);

       return new ResponseEntity<>(isAlowed, HttpStatus.ACCEPTED);

    }
}
