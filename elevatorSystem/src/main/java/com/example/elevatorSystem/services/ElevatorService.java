package com.example.elevatorSystem.services;


import com.example.elevatorSystem.model.Elevator;
import com.example.elevatorSystem.model.Request;
import com.example.elevatorSystem.repository.ElevatorRepository;
import com.example.elevatorSystem.strategy.DispatchStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ElevatorService {

    private final ElevatorRepository elevatorRepository;
    private final ExecutorService executorService;
    private final DispatchStrategy dispatchStrategy;
    private final List<Elevator> elevatorList;


    public ElevatorService(ElevatorRepository elevatorRepository, DispatchStrategy dispatchStrategy, List<Elevator> elevatorList) {
        this.elevatorRepository = elevatorRepository;
        this.executorService = Executors.newFixedThreadPool(10);
        this.dispatchStrategy = dispatchStrategy;
        this.elevatorList = elevatorRepository.getElevatorList();
    }

    public void addElevator(Elevator elevator){

        elevatorRepository.addElevator(elevator);
    }

    public List<Elevator> getElevatorList(){

        return elevatorRepository.getElevatorList();
    }

    public void runElevators(){
        List<Elevator> elevatorList = this.getElevatorList();

        for(Elevator elevator : elevatorList){
            executorService.submit(elevator);
        }

    }

    public void addInternalRequests(Integer elevatorId, Request request) throws Exception {

        Optional<Elevator> elevatorOptional = elevatorRepository.getElevatorList().stream()
                .filter(elevatorIn -> elevatorIn.getElevatorId() == elevatorId).findFirst();

        if(elevatorOptional.isEmpty()){
            throw new Exception("Elevator not present");
        }

        Elevator elevator = elevatorOptional.get();
        elevator.addRequest(request);

    }

    public void addExternalRequests(Request request){

       Elevator elevator = dispatchStrategy.selectOptimalElevator(elevatorList, request);
       elevator.addRequest(request);
    }
}
