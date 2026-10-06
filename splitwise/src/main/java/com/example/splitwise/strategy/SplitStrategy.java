package com.example.splitwise.strategy;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpensesWithSplit;

public interface SplitStrategy {

    ExpensesWithSplit split(Expense expense);
}
