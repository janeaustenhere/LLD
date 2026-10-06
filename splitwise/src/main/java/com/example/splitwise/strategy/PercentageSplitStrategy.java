package com.example.splitwise.strategy;


import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpensesWithSplit;
import org.springframework.stereotype.Component;

@Component
public class PercentageSplitStrategy implements SplitStrategy{
    @Override
    public ExpensesWithSplit split(Expense expense) {
        return null;
    }
}
