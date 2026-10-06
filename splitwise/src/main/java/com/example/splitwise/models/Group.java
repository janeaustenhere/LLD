package com.example.splitwise.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Group {

    private final String id;
    private final String name;
    @JsonIgnore
    private final List<User> members = new ArrayList<>();
    @JsonIgnore
    private final List<ExpensesWithSplit> expenseList = new ArrayList<>();
    @JsonIgnore
    private final Map<User, BalanceSheet> balanceSheetMap = new HashMap<>();

}
