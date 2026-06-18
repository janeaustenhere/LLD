package com.example.rateLimiter.services;

import com.example.rateLimiter.enums.RateLimiterType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class TokenBucketRateLimiter extends RateLimiter{

    @Value("${rate.limiter.token-bucket.window-in-seconds}")
    private  Long timeWindowInSeconds;

    @Value("${rate.limiter.token-bucket.max-requests}")
    private Integer numberOfAllowedRequest;

    private final Map<String,Integer> tokens = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRefillTime = new HashMap<>();


    public TokenBucketRateLimiter() {
        super(RateLimiterType.TOKEN_BUCKET);
    }

    @Override
    public boolean allowRequest(String userId) {
        refillToken(userId);
        AtomicBoolean allowed = new AtomicBoolean(false);
        if(tokens.get(userId) != null && tokens.get(userId) > 0){

            allowed.set(true);
            int currentCount = tokens.get(userId);
            currentCount--;
            tokens.put(userId,currentCount);
        }

        return allowed.get();
    }

    private void refillToken(String userId){

       if(!lastRefillTime.containsKey(userId)){
           tokens.put(userId,this.numberOfAllowedRequest);
           lastRefillTime.put(userId, System.currentTimeMillis());
           return;
       }

       Long previousRefillTime = lastRefillTime.get(userId);
       Long currentTimeInmillis = System.currentTimeMillis();
       Long elapseTimeInSeconds = (currentTimeInmillis - previousRefillTime)/1000;

       int refillRate = Math.toIntExact(timeWindowInSeconds / numberOfAllowedRequest.intValue());

       int refillTokens = Math.toIntExact(elapseTimeInSeconds / refillRate);

       int currentToken = tokens.get(userId);

       int finalTokens = Math.min(numberOfAllowedRequest,refillTokens + currentToken);

       tokens.put(userId,finalTokens);
       lastRefillTime.put(userId,currentTimeInmillis);

    }
}
