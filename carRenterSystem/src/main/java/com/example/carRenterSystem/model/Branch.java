package com.example.carRenterSystem.model;


import com.example.carRenterSystem.enums.VehicleType;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class Branch {

    String branchId;
    String name;
    Address address;
    Map<VehicleType, List<Vehicle>> vehicleMap = new HashMap<>();


}
