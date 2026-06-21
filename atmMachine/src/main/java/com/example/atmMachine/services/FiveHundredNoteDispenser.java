package com.example.atmMachine.services;

import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component("fiveHundredNoteDispenser")
public class FiveHundredNoteDispenser implements CashDispenser {

    CashDispenser nextCashDispenser;
    @Override
    public void setNextDispenser(CashDispenser cashDispenser) {
            this.nextCashDispenser = cashDispenser;
    }

    @Override
    public boolean canDispense(ATM atm, Card card, BigDecimal amount) {

        return amount.compareTo(BigDecimal.valueOf(500)) >= 0;
    }

    @Override
    public void dispense(ATM atm, Card card, BigDecimal amount) {
        if(atm.getTotalAmount().compareTo(amount) < 0){
            throw new RuntimeException("Not enough money in ATM");
        }

        if(!canDispense(atm,card,amount)){
            nextCashDispenser.dispense(atm, card, amount);

        }

        int noteCount = Math.min(amount.divide(BigDecimal.valueOf(500)).intValue(),
                atm.getTotalCountForTwoThousandsNotes());
        log.info("Five hundred rupees note dispense: count:: {}", noteCount);
        atm.setTotalCountForTwoThousandsNotes(atm.getTotalCountForTwoThousandsNotes()-noteCount);
        BigDecimal remainingAmount = amount.subtract(BigDecimal.valueOf(500L *noteCount));
        if(remainingAmount.compareTo(BigDecimal.valueOf(0)) > 0 && nextCashDispenser != null ) {
            nextCashDispenser.dispense(atm,card,amount);
        }


    }
}
