package com.example.elevatorSystem.strategy;

import com.example.elevatorSystem.model.Elevator;
import com.example.elevatorSystem.model.Request;

import java.util.List;

public interface DispatchStrategy {

    Elevator selectOptimalElevator(List<Elevator> elevatorList, Request request);
}
