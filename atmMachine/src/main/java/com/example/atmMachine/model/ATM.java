package com.example.atmMachine.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ATM {

    String atmId;
    Address address;
    BigDecimal totalAmount;
    int totalCountForTwoThousandsNotes;
    int totalCountForFiveHundredsNotes;
    int totalCountForHundredsNotes;

}
