package com.example.uber.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EstimatedFareAndTime {

    double fare;
    long estimatedTime;
}
