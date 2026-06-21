package com.example.atmMachine.services;

import com.example.atmMachine.enums.ATMMachineState;
import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component("idealState")
public class IdealState implements ATMState{

    ATMState nextState;

    @Override
    public void insertCard(ATM atm, Card card) {

        log.info("Insert your card:");
        nextState.authenticateCard(atm,card,"49875");

    }

    @Override
    public void authenticateCard(ATM atm, Card card, String pin) {
        log.info("Card hasn't been inserted yet");
    }

    @Override
    public void withdrawAmount(ATM atm, Card card, BigDecimal amount) {
        log.info("Card hasn't been inserted yet");
    }

    @Override
    public void dispenseCard(ATM atm, Card card) {
        log.info("Card hasn't been inserted yet");
    }

    @Override
    public void setNextATMState(ATMState state) {
       this.nextState = state;
    }

    @Override
    public ATMState getCurrentState() {
        return this;
    }

    @Override
    public void selectOption(ATM atm, Card card,ATMMachineState atmMachineState) {
        return;
    }
}
