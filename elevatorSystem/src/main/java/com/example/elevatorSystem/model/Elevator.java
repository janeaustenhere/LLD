package com.example.elevatorSystem.model;


import com.example.elevatorSystem.enums.Direction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Data
public class Elevator implements Runnable{

    int id;
    @JsonIgnore
    AtomicInteger currentFloor;
    @JsonIgnore
    AtomicReference<Direction> currentDirection;
    @JsonIgnore
    ConcurrentSkipListSet<Request> upRequests;
    @JsonIgnore
    ConcurrentSkipListSet<Request> downRequests;

    public Elevator(int id){
        this.id = id;
        this.currentFloor = new AtomicInteger(0);
        this.currentDirection = new AtomicReference<>(Direction.IDLE);
        this.upRequests = new ConcurrentSkipListSet<>((a,b)
                -> a.getRequestedFloor() - b.getRequestedFloor());
        this.downRequests = new ConcurrentSkipListSet<>((a,b)
                -> b.getRequestedFloor() - a.getRequestedFloor());
    }

    public void addRequest(Request request){

        if(request.getRequestedDirection().equals(Direction.UP)){
            upRequests.add(request);
        }else{
            downRequests.add(request);
        }

        if(currentDirection.get().equals(Direction.IDLE)){

            currentDirection.set(request.getRequestedFloor() > currentFloor.get()
                    ? Direction.UP: Direction.DOWN);
        }
    }

    private void processRequests() throws InterruptedException {

        if(currentDirection.get().equals(Direction.UP)
                || currentDirection.get().equals(Direction.IDLE)){

            processUpRequests();
            processDownRequests();

        }else{
            processDownRequests();
            processUpRequests();
        }

        if(upRequests.isEmpty() && downRequests.isEmpty()){
            currentDirection.set(Direction.IDLE);
        }
    }

    private void processUpRequests() throws InterruptedException {

        while(!upRequests.isEmpty()){
            int floor = Objects.requireNonNull(upRequests.pollFirst()).getRequestedFloor();
            moveToFloor(floor);

        }

        if(!downRequests.isEmpty()){
            currentDirection.set(Direction.DOWN);

        }
    }

    private void processDownRequests() throws InterruptedException {

        while (!downRequests.isEmpty()){
            int floor = Objects.requireNonNull(downRequests.pollFirst()).getRequestedFloor();
            moveToFloor(floor);

        }

        if(!upRequests.isEmpty()){
            currentDirection.set(Direction.UP);
        }
    }

    private void moveToFloor(int floor) throws InterruptedException {

        System.out.println("Elevator : "  + this.id + " is moving from floor: " + currentFloor.get() + " to "
        + floor + " floor");

        Thread.sleep(1000L * Math.abs(currentFloor.get() - floor));
        currentFloor.set(floor);

    }

    public int getCurrentFloor(){
        return this.currentFloor.get();
    }

    public Direction getCurrentDirection(){
        return currentDirection.get();
    }

    public int getElevatorId(){
        return this.id;
    }

    @Override
    public void run() {

        while (true){


            try {
                processRequests();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               break;
            }
        }

    }
}
