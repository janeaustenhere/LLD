package com.example.atmMachine.services;

import com.example.atmMachine.enums.ATMMachineState;
import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component("cardAuthenticatedState")
public class CardAuthenticatedState implements ATMState{


    private ATMState nextATMState;



    @Override
    public void insertCard(ATM atm, Card card) {
        log.info("Card has been inserted");
    }

    @Override
    public void authenticateCard(ATM atm, Card card, String pin) {
        log.info("Card has been Authenticated");
        nextATMState.selectOption(atm, card,ATMMachineState.DISPENSED_MONEY);
;    }

    @Override
    public void withdrawAmount(ATM atm, Card card, BigDecimal amount) {


    }

    @Override
    public void dispenseCard(ATM atm, Card card) {
        log.info("Card has been dispensed");
    }

    @Override
    public void setNextATMState(ATMState state) {
        this.nextATMState = state;
    }

    @Override
    public ATMState getCurrentState() {
        return this;
    }

    @Override
    public void selectOption(ATM atm, Card card,ATMMachineState atmMachineState) {

    }
}
