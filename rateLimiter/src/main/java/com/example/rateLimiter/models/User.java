package com.example.rateLimiter.models;


import com.example.rateLimiter.enums.UserTier;
import lombok.Data;

@Data
public class User {

    String userId;

    UserTier tier;
}
