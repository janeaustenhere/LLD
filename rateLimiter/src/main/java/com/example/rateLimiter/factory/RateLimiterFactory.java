package com.example.rateLimiter.factory;


import com.example.rateLimiter.config.RateLimiterConfiguration;
import com.example.rateLimiter.enums.RateLimiterType;
import com.example.rateLimiter.services.*;
import org.springframework.stereotype.Component;

import java.lang.runtime.SwitchBootstraps;

@Component
public class RateLimiterFactory {

    private final TokenBucketRateLimiter tokenBucketRateLimiter;
    private final FixedWindowRateLimiter fixedWindowRateLimiter;
    private final SlidingWindowLogRateLimiter slidingWindowLogRateLimiter;

    public RateLimiterFactory(TokenBucketRateLimiter tokenBucketRateLimiter, FixedWindowRateLimiter fixedWindowRateLimiter, SlidingWindowLogRateLimiter slidingWindowLogRateLimiter) {
        this.tokenBucketRateLimiter = tokenBucketRateLimiter;
        this.fixedWindowRateLimiter = fixedWindowRateLimiter;
        this.slidingWindowLogRateLimiter = slidingWindowLogRateLimiter;
    }


    public RateLimiter createRateLimiterObject(RateLimiterType rateLimiterType){
        return switch (rateLimiterType) {
            case RateLimiterType.SLIDING_WINDOW_LOG -> slidingWindowLogRateLimiter;
            case RateLimiterType.FIXED_WINDOW -> fixedWindowRateLimiter;
            case RateLimiterType.TOKEN_BUCKET -> tokenBucketRateLimiter;

            default -> throw new IllegalStateException("Unexpected value: " + rateLimiterType);
        };
    }
}
