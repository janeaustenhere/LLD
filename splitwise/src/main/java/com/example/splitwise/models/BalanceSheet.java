package com.example.splitwise.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class BalanceSheet {

        String userId;
        BigDecimal amountPaid = new BigDecimal(0);
        BigDecimal totalExpenses = new BigDecimal(0);
        Map<User,BigDecimal> balancesWithUsers = new HashMap<>();


}
