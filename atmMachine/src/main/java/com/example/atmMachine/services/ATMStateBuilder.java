package com.example.atmMachine.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ATMStateBuilder {

    private final ATMState cardAuthenticatedState;
    private final ATMState cardInsertedState;
    private final ATMState idealState;
    private final ATMState selectOptionState;


    public ATMStateBuilder(@Qualifier("cardAuthenticatedState") ATMState cardAuthenticatedState,
                           @Qualifier("cardInsertedState") ATMState cardInsertedState,
                           @Qualifier("idealState") ATMState idealState,
                           @Qualifier("selectOptionState") ATMState selectOptionState) {
        this.cardAuthenticatedState = cardAuthenticatedState;
        this.cardInsertedState = cardInsertedState;
        this.idealState = idealState;
        this.selectOptionState = selectOptionState;
    }

    public ATMState getATMStateSequence(){
        idealState.setNextATMState(cardInsertedState);
        cardInsertedState.setNextATMState(selectOptionState);
       /// cardAuthenticatedState.setNextATMState(selectOptionState);
      return idealState;

    }


}
