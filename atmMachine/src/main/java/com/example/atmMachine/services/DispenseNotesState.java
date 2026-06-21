package com.example.atmMachine.services;

import com.example.atmMachine.enums.ATMMachineState;
import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component("dispenseNotesState")
public class DispenseNotesState implements ATMState{

    private final CashDispenserChainBuilder cashDispenserChainBuilder;


    private final ATMState nextATMState;

    public DispenseNotesState(CashDispenserChainBuilder cashDispenserChainBuilder,
                              @Qualifier("dispenseCardState") ATMState nextATMState) {
        this.cashDispenserChainBuilder = cashDispenserChainBuilder;
        this.nextATMState = nextATMState;
    }

    @Override
    public void insertCard(ATM atm, Card card) {

    }

    @Override
    public void authenticateCard(ATM atm, Card card, String pin) {

    }

    @Override
    public void withdrawAmount(ATM atm, Card card, BigDecimal amount) {
        if(atm.getTotalAmount().compareTo(amount) < 0){
            throw new RuntimeException("Not enough money in ATM");
        }

        CashDispenser cashDispenser = cashDispenserChainBuilder.getChainObject();
        cashDispenser.dispense(atm, card,amount);
        nextATMState.dispenseCard(atm,card);
        log.info("Amount has been dispensed");

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
        return;
    }
}
