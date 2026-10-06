package com.example.splitwise.services;


import com.example.splitwise.factory.SplitTypeObjectFactory;
import com.example.splitwise.models.BalanceSheet;
import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpensesWithSplit;
import com.example.splitwise.repositories.GroupRepository;
import com.example.splitwise.strategy.SplitStrategy;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final GroupRepository groupRepository;
    private final SplitTypeObjectFactory splitTypeObjectFactory;
    private final BalanceSheetService balanceSheetService;

    public ExpenseService(GroupRepository groupRepository, SplitTypeObjectFactory splitTypeObjectFactory, BalanceSheetService balanceSheetService) {
        this.groupRepository = groupRepository;

        this.splitTypeObjectFactory = splitTypeObjectFactory;
        this.balanceSheetService = balanceSheetService;
    }

    public void addExpenses(String groupId, Expense expense) {

        SplitStrategy splitStrategy = this.splitTypeObjectFactory.getSplitStrategy(expense.getSplitStrategy());
        ExpensesWithSplit expensesWithSplit = splitStrategy.split(expense);
        this.groupRepository.addExpenses(groupId,expensesWithSplit);
        balanceSheetService.updateBalanceSheets(groupId,expensesWithSplit);

    }

}
