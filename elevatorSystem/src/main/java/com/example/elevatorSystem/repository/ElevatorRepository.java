package com.example.elevatorSystem.repository;


import com.example.elevatorSystem.model.Elevator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ElevatorRepository {

    List<Elevator> elevatorList = new ArrayList<>();

    public void addElevator(Elevator elevator){
        elevatorList.add(elevator);


    }

    public List<Elevator> getElevatorList(){
        return elevatorList;
    }




}
