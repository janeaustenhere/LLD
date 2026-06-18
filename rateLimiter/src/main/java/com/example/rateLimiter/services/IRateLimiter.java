package com.example.rateLimiter.services;

import com.example.rateLimiter.config.RateLimiterConfiguration;

public interface IRateLimiter {

    boolean allowRequest(String userId);
}
