package com.example.atmMachine.services;

import com.example.atmMachine.enums.ATMMachineState;
import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.LinkOption;

@Slf4j
@Component("selectOptionState")
public class SelectOptionState implements ATMState{


    private final ATMState nextDispenseNoteState;

    public SelectOptionState( @Qualifier("dispenseNotesState") ATMState nextATMState) {
        this.nextDispenseNoteState = nextATMState;
    }

    @Override
    public void insertCard(ATM atm, Card card) {

    }

    @Override
    public void authenticateCard(ATM atm, Card card, String pin) {

    }

    @Override
    public void withdrawAmount(ATM atm, Card card, BigDecimal amount) {

    }

    @Override
    public void dispenseCard(ATM atm, Card card) {

    }

    @Override
    public void setNextATMState(ATMState state) {

    }

    @Override
    public ATMState getCurrentState() {
        return this;
    }

    @Override
    public void selectOption(ATM atm, Card card,ATMMachineState atmMachineState) {
        log.info("select option first: ");
       if(atmMachineState.equals(ATMMachineState.DISPENSED_MONEY)){
           BigDecimal amount = BigDecimal.valueOf(9000);
           nextDispenseNoteState.withdrawAmount(atm,card,amount);
       }
    }
}
