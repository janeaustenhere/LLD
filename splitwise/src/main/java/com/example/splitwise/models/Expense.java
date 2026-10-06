package com.example.splitwise.models;

import com.example.splitwise.strategy.SplitStrategy;
import com.example.splitwise.strategy.SplitStrategyType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class Expense {

    String id;
    String description;
    List<User> participants;
    BigDecimal totalAmount;
    String paidByUser;
    SplitStrategyType splitStrategy;

}
