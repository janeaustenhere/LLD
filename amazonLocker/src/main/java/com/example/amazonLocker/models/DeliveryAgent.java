package com.example.amazonLocker.models;


import lombok.Data;

import java.util.List;

@Data
public class DeliveryAgent {

    Long agentId;
    String agentName;
    List<String> serviceableZipcodes;




}
