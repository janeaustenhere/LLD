package com.example.splitwise.strategy;


import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpensesWithSplit;
import com.example.splitwise.models.Split;
import com.example.splitwise.models.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public ExpensesWithSplit split(Expense expense) {

        BigDecimal totalAmount = expense.getTotalAmount();
        List<User> participantList = expense.getParticipants();
        int totalParticipant  = participantList.size();
        BigDecimal amountPerParticipant = totalAmount.divide(BigDecimal.valueOf(totalParticipant),2, RoundingMode.HALF_EVEN);
        ExpensesWithSplit expensesWithSplit = new ExpensesWithSplit(expense.getId(), expense.getDescription(),
                new ArrayList<>(), totalAmount, expense.getPaidByUser());
        for (User user :participantList){
            Split split = new Split(user, amountPerParticipant);
            expensesWithSplit.getSplits().add(split);
        }
        return expensesWithSplit;
    }
}
