package com.example.bookmyshow.provider;

public interface LockProvider {

    boolean tryLock(String key,String userId);
    boolean isLockExpire(String key);
    boolean lockByUser(String key, String userId);
    void unlock(String key);
}
