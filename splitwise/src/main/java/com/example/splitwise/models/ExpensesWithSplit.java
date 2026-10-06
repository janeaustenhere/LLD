package com.example.splitwise.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ExpensesWithSplit {

    String id;
    String description;
    List<Split> splits;
    BigDecimal totalAmount;
    String paidByUser;
}
