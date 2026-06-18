package com.example.amazonLocker.models;

import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class Slot {

    Long slotId;
    Package storedPackage;
    SlotSize size;
    AtomicBoolean available = new AtomicBoolean(true);

    public boolean isAvailable(){
        return available.get();
    }

    public void acquire(){
        available.set(false);
    }

    public void release(){
        available.set(true);
    }







}
