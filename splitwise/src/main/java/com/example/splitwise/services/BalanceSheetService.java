package com.example.splitwise.services;


import com.example.splitwise.models.*;
import com.example.splitwise.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class BalanceSheetService {

    private final GroupRepository groupRepository;

    public BalanceSheetService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void updateBalanceSheets(String groupId, ExpensesWithSplit expensesWithSplit) {

        Group group = groupRepository.getGroupDetails(groupId);

        for (Split split : expensesWithSplit.getSplits()) {
            User user = split.getUser();
            BigDecimal userExpense = split.getAmountToPay();
            BalanceSheet balanceSheet = group.getBalanceSheetMap().get(user);
            balanceSheet.setTotalExpenses(balanceSheet.getTotalExpenses().add(userExpense));
            if (user.getUserName().equals(expensesWithSplit.getPaidByUser())) {
                balanceSheet.setAmountPaid(balanceSheet.getAmountPaid().add(expensesWithSplit.getTotalAmount()));
                Map<User, BigDecimal> balancesWithUser = balanceSheet.getBalancesWithUsers();
                for (Map.Entry<User, BigDecimal> map : balancesWithUser.entrySet()) {

                    BigDecimal newAmount = map.getValue().add(userExpense.negate());
                    map.setValue(newAmount);
                }
                balanceSheet.setBalancesWithUsers(balancesWithUser);
            }
        }
    }
}
