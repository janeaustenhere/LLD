package com.example.atmMachine.services;

import com.example.atmMachine.enums.ATMMachineState;
import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;

import java.math.BigDecimal;

public interface ATMState {

    void insertCard(ATM atm, Card card);
    void authenticateCard(ATM atm, Card card, String pin);
    void withdrawAmount(ATM atm, Card card, BigDecimal amount);
    void dispenseCard(ATM atm, Card card);
    void setNextATMState(ATMState state);
    ATMState getCurrentState();
    void selectOption(ATM atm, Card card,ATMMachineState atmMachineState);


}
