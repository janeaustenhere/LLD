package com.example.rateLimiter.config;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RateLimiterConfiguration {

    Long timeWindow;
    Integer numberOrRequest;
}
