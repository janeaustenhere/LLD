package com.example.rateLimiter.services;


import com.example.rateLimiter.config.RateLimiterConfiguration;
import com.example.rateLimiter.enums.RateLimiterType;
import com.example.rateLimiter.enums.UserTier;
import com.example.rateLimiter.factory.RateLimiterFactory;
import com.example.rateLimiter.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RateLimiterService {

    private final Map<UserTier, RateLimiter> rateLimiters = new HashMap<>();

    private final RateLimiterFactory rateLimiterFactory;

    public RateLimiterService(RateLimiterFactory rateLimiterFactory) {

        this.rateLimiterFactory = rateLimiterFactory;
        rateLimiters.put(UserTier.FREE,
                this.rateLimiterFactory.createRateLimiterObject(RateLimiterType.FIXED_WINDOW));

        rateLimiters.put(UserTier.PREMIUM,
                this.rateLimiterFactory.createRateLimiterObject(RateLimiterType.TOKEN_BUCKET));

    }

    public boolean allowedRequest(User user){
       RateLimiter rateLimiter = rateLimiters.get(user.getTier());

       if (rateLimiter == null){

           throw  new RuntimeException("No Rate Limiter configured for user Tier:: " + user.getTier());

       }

       return rateLimiter.allowRequest(user.getUserId());


    }




}
