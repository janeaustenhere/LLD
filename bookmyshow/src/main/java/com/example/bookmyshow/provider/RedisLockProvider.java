package com.example.bookmyshow.provider;

import org.springframework.stereotype.Component;

@Component("redisLockProvider")
public class RedisLockProvider implements LockProvider{
    @Override
    public boolean tryLock(String key, String userId) {
        return false;
    }

    @Override
    public boolean isLockExpire(String key) {
        return false;
    }

    @Override
    public boolean lockByUser(String key, String userId) {
        return false;
    }

    @Override
    public void unlock(String key) {
        return;
    }
}
