package com.example.splitwise.repositories;


import com.example.splitwise.models.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GroupRepository {

    Map<String, Group> groupMap = new ConcurrentHashMap<>();

    public void createGroup(Group group){
        groupMap.put(group.getId(),group);
    }

    public Group getGroupDetails(String groupId){
        return groupMap.get(groupId);
    }

    public List<Group> groupList(){
        return groupMap.values().stream().toList();
    }

    public void addUser(String groupId, User user){
       this.groupMap.get(groupId).getMembers().add(user);
       this.groupMap.get(groupId).getBalanceSheetMap().put(user,
               new BalanceSheet(user.getId(), new BigDecimal(0),
                       new BigDecimal(0), new HashMap<>()));
    }

    public void addExpenses(String groupId,ExpensesWithSplit expensesWithSplit){
        this.groupMap.get(groupId).getExpenseList().add(expensesWithSplit);
    }
 }
