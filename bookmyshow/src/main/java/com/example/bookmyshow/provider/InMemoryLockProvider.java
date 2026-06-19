package com.example.bookmyshow.provider;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component("inMemoryLockProvider")
public class InMemoryLockProvider implements LockProvider{

    Map<String,Expire> lockMap = new ConcurrentHashMap<>();
    private ScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    public void init(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this::removeExpireLock,8,8, TimeUnit.MINUTES);
    }

    // key = theatreId_screenId_seatId
    @Override
    public boolean tryLock(String key, String userId) {
       if(lockMap.containsKey(key)){
           Expire expire = lockMap.get(key);
           if(expire.expiryTime() > System.currentTimeMillis()){
               long duration = Duration.ofMinutes(8).toMillis();
               expire = new Expire(key, System.currentTimeMillis()+duration,userId);
               lockMap.put(key, expire);
               return true;
           }else{
               return false;
           }
       }else{
           long duration = Duration.ofMinutes(8).toMillis();
           Expire expire = new Expire(key, System.currentTimeMillis()+duration,userId);
           lockMap.put(key, expire);
           return true;
       }
    }

    @Override
    public boolean isLockExpire(String key) {
        if(lockMap.containsKey(key)){
            Expire expire = lockMap.get(key);
            return expire.expiryTime() > System.currentTimeMillis();
        }

        return true;
    }

    @Override
    public boolean lockByUser(String key, String userId) {
        if(lockMap.containsKey(key)){
            Expire expire = lockMap.get(key);
            return expire.userId().equals(userId) && expire.expiryTime() > System.currentTimeMillis();
        }

        return false;
    }

    @Override
    public void unlock(String key) {
        lockMap.remove(key);
    }

    private void removeExpireLock(){
        long currentTime = System.currentTimeMillis();

        lockMap.entrySet()
                .removeIf(stringExpireEntry ->
                        stringExpireEntry.getValue().expiryTime() < currentTime);


    }

    @PreDestroy
    public void shutDown(){
        scheduledExecutorService.shutdown();
    }
}


