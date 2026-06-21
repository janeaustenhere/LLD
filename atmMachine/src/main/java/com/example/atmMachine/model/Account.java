package com.example.atmMachine.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {

    String accountId;
    String accountHolderName;
    BigDecimal currentAmount;
}
