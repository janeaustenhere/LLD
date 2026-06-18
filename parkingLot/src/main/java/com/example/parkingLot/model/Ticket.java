package com.example.parkingLot.model;


import com.example.parkingLot.enums.PaymentMode;
import com.example.parkingLot.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Ticket {

    String ticketId;
    LocalDateTime entryTime;
    Vehicle vehicle;
    LocalDateTime exitTime;
    double amountToPay;
    PaymentMode paymentMode;
    PaymentStatus paymentStatus;
}
