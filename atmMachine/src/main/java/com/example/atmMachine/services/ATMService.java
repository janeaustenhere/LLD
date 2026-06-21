package com.example.atmMachine.services;


import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;
import com.example.atmMachine.repository.ATMRepositorty;
import org.springframework.stereotype.Service;

@Service
public class ATMService {

    private final  ATMRepositorty atmRepositorty;
    private ATMState atmState;

    private final ATMStateBuilder atmStateBuilder;

    public ATMService(ATMRepositorty atmRepositorty, ATMStateBuilder atmStateBuilder) {
        this.atmRepositorty = atmRepositorty;
        this.atmStateBuilder = atmStateBuilder;
    }

    public ATM addATMMachine(ATM atm){
        return atmRepositorty.addATMMachine(atm);
    }

    public ATM addCashToATM(ATM atm){
        return atmRepositorty.addCashToATM(atm);
    }

    public void startATMState(String atmId, Card card){
        atmState = atmStateBuilder.getATMStateSequence();
        ATM atm = atmRepositorty.getATMByID(atmId);
        atmState.insertCard(atm,card);
    }
}
