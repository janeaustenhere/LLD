package com.example.carRenterSystem.strategy;

import com.example.carRenterSystem.model.Booking;

public interface PaymentStrategy {

    void Pay(Booking booking);
}
