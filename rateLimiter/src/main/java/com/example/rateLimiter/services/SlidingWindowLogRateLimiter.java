package com.example.rateLimiter.services;


import com.example.rateLimiter.config.RateLimiterConfiguration;
import com.example.rateLimiter.enums.RateLimiterType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class SlidingWindowLogRateLimiter extends RateLimiter {

    @Value("${rate.limiter.sliding-window.window-in-seconds}")
    private  Long timeWindowInSeconds;

    @Value("${rate.limiter.sliding-window.max-requests}")
    private  Integer numberOfAllowedRequest;

    private final Map<String, Queue<Long>> timeLogMap = new ConcurrentHashMap<>();

    public SlidingWindowLogRateLimiter() {
        super(RateLimiterType.SLIDING_WINDOW_LOG);

    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        Queue<Long> deque = new ArrayDeque<>();
        Long currentTimeInMilles = System.currentTimeMillis();
        if(!timeLogMap.containsKey(userId)){
            allowed.set(true);
            deque.add(currentTimeInMilles);
            timeLogMap.put(userId,deque);
        }else{
            deque = timeLogMap.get(userId);
            while(!deque.isEmpty() &&
                    ((currentTimeInMilles - deque.peek())/1000 > timeWindowInSeconds)){
                deque.poll();
            }
            if(deque.size() < numberOfAllowedRequest){
                deque.add(currentTimeInMilles);
                allowed.set(true);
            }
            timeLogMap.put(userId,deque);

        }

        return allowed.get();

    }
}
