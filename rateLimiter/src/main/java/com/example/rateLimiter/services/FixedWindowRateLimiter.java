package com.example.rateLimiter.services;

import com.example.rateLimiter.enums.RateLimiterType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class FixedWindowRateLimiter extends RateLimiter {

    Map<String, Integer> requestCount = new ConcurrentHashMap<>();

    Map<String,Integer> windowCountForUser = new HashMap<>();

    @Value("${rate.limiter.fixed-window.window-in-seconds}")
    private  Long timeWindowInSecods;

    @Value("${rate.limiter.fixed-window.max-requests}")
    private  Integer numberOfAllowedRequest;

    public FixedWindowRateLimiter() {
        super(RateLimiterType.FIXED_WINDOW);
    }

    @Override
    public boolean allowRequest(String userId) {

        AtomicBoolean allowed = new AtomicBoolean(false);

        Long currentTimeInMiliis = System.currentTimeMillis();
        Integer timeInSeconds = Math.toIntExact(currentTimeInMiliis / 1000);

        Integer currentWindow = Math.toIntExact(timeInSeconds / timeWindowInSecods);

        Integer previousWindow = windowCountForUser.get(userId);

        if(previousWindow != null && currentWindow.equals(previousWindow)){

            Integer currentCount = requestCount.get(userId);
            if(currentCount != null && currentCount >= this.numberOfAllowedRequest){
                allowed.set(false);
            }else{
                currentCount++;
                requestCount.put(userId,currentCount);
                allowed.set(true);

            }
        }else{

            requestCount.put(userId,1);
            windowCountForUser.put(userId,currentWindow);
            allowed.set(true);
        }

       return  allowed.get();

    }
}
