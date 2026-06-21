package com.example.atmMachine.services;

import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;

import java.math.BigDecimal;

public interface CashDispenser {

    void setNextDispenser(CashDispenser cashDispenser);
    boolean canDispense(ATM atm, Card card, BigDecimal amount);
    void dispense(ATM atm, Card card, BigDecimal amount);
}
