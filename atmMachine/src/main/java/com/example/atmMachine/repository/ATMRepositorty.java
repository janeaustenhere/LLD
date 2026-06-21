package com.example.atmMachine.repository;


import com.example.atmMachine.model.ATM;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ATMRepositorty {

    Map<String, ATM> atmMap = new HashMap<>();

    public ATM addATMMachine(ATM atm){

        String atmId = UUID.randomUUID().toString();
        atm.setAtmId(atmId);
        atmMap.put(atmId,atm);
        return atm;
    }

    public ATM addCashToATM(ATM atm){
        atmMap.put(atm.getAtmId(), atm);
        return atm;
    }

    public ATM getATMByID(String atmId){
        return  atmMap.get(atmId);
    }
}
