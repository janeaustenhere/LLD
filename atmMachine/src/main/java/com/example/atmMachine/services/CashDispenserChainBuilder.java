package com.example.atmMachine.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CashDispenserChainBuilder {

    private final CashDispenser twoThousandNoteDispenser;
    private final CashDispenser fiveHundredNoteDispenser;
    private final CashDispenser hundredNoteDispenser;

    public CashDispenserChainBuilder(@Qualifier("twoThousandNoteDispenser") CashDispenser twoThousandNoteDispenser,
                                     @Qualifier("fiveHundredNoteDispenser") CashDispenser fiveHundredNoteDispenser,
                                     @Qualifier("hundredNoteDispenser") CashDispenser hundredNoteDispenser) {
        this.twoThousandNoteDispenser = twoThousandNoteDispenser;
        this.fiveHundredNoteDispenser = fiveHundredNoteDispenser;
        this.hundredNoteDispenser = hundredNoteDispenser;
    }

    public CashDispenser getChainObject(){
        twoThousandNoteDispenser.setNextDispenser(fiveHundredNoteDispenser);
        fiveHundredNoteDispenser.setNextDispenser(hundredNoteDispenser);
        return twoThousandNoteDispenser;
    }
}
