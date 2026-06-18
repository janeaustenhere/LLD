package com.example.amazonLocker.models;


import lombok.Data;

@Data
public class Package {

    Long packageId;
    PackageStatus packageStatus;
    PackageSize packageSize;
    Long customerId;
    Long agentId;
    Long lockerId;
    Long slotId;
}
