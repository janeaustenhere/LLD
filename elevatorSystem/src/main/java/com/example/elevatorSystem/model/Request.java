package com.example.elevatorSystem.model;

import com.example.elevatorSystem.enums.Direction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Request {

    @Setter(AccessLevel.NONE)
    private String requestId;
    @Setter(AccessLevel.NONE)
    private int requestedFloor;
    @Setter(AccessLevel.NONE)
    private Direction requestedDirection;

}
