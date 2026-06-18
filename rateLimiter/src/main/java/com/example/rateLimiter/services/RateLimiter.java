package com.example.rateLimiter.services;

import com.example.rateLimiter.config.RateLimiterConfiguration;
import com.example.rateLimiter.enums.RateLimiterType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class RateLimiter implements IRateLimiter {

    RateLimiterType rateLimiterType;

}
