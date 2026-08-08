package com.example.elevatorSystem.strategy;

import com.example.elevatorSystem.enums.Direction;
import com.example.elevatorSystem.model.Elevator;
import com.example.elevatorSystem.model.Request;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NearestElevatorDispatchStrategy implements DispatchStrategy{
    @Override
    public Elevator selectOptimalElevator(List<Elevator> elevatorList, Request request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        boolean isSameDirection = false;

        for(Elevator elevator : elevatorList){

            int distance = Math.abs(request.getRequestedFloor() - elevator.getCurrentFloor());

            isSameDirection = ((elevator.getCurrentDirection().equals(Direction.UP)
                    && request.getRequestedFloor() > elevator.getCurrentFloor())
                    || (elevator.getCurrentDirection().equals(Direction.DOWN)
                    && request.getRequestedFloor() < elevator.getCurrentFloor()));

            if(distance < minDistance && (isSameDirection || elevator.getCurrentDirection().equals(Direction.IDLE))){
                bestElevator = elevator;
            }
            minDistance = Math.min(minDistance, Math.abs(request.getRequestedFloor() - elevator.getCurrentFloor()));

        }

        return bestElevator != null ? bestElevator : elevatorList.getFirst();
    }
}
