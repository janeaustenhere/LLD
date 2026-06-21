package com.example.atmMachine.services;

import com.example.atmMachine.enums.ATMMachineState;
import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component("dispenseCardState")
public class DispenseCardState implements ATMState{

    ATMState nextATMState;
    @Override
    public void insertCard(ATM atm, Card card) {
        log.info("card has been inserted");
    }

    @Override
    public void authenticateCard(ATM atm, Card card, String pin) {



    }

    @Override
    public void withdrawAmount(ATM atm, Card card, BigDecimal amount) {

    }

    @Override
    public void dispenseCard(ATM atm, Card card) {
      log.info(("Card being dispensed"));

    }

    @Override
    public void setNextATMState(ATMState state) {
        this.nextATMState = state;
    }

    @Override
    public ATMState getCurrentState() {
        return null;
    }

    @Override
    public void selectOption(ATM atm, Card card,ATMMachineState atmMachineState) {
        return;
    }
}
