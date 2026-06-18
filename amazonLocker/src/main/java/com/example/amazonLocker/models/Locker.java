package com.example.amazonLocker.models;


import lombok.Data;

import java.util.Map;

@Data
public class Locker {


    Long lockerID;
    LockerStatus lockerStatus;
    String lockerName;
    String zipcode;
    Map<String,Slot> slots; // slotId, object

    // implements method getSlotById
    // getAllSlots


}
