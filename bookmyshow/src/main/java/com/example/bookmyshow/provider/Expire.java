package com.example.bookmyshow.provider;

import java.time.LocalDateTime;

public record Expire(String key,
                     long expiryTime,
                     String userId) {
}
