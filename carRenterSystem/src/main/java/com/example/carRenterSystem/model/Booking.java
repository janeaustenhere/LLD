package com.example.carRenterSystem.model;

import com.example.carRenterSystem.enums.BookingStatus;
import com.example.carRenterSystem.enums.VehicleType;
import com.example.carRenterSystem.strategy.PaymentStrategy;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {

    String bookingId;
    String pickUpBranchId;
    String dropBranchId;
    LocalDateTime pickupTime;
    LocalDateTime dropTime;
    VehicleType vehicleType;
    String VehicleId;
    PaymentStrategy paymentStrategy;
    BookingStatus bookingStatus = BookingStatus.CREATED;
    double amount;
}
