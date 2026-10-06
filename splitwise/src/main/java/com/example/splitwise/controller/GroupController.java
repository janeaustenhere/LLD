package com.example.splitwise.controller;


import com.example.splitwise.models.Expense;
import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import com.example.splitwise.services.DebtSimplificationService;
import com.example.splitwise.services.ExpenseService;
import com.example.splitwise.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;
    private final ExpenseService expenseService;
    private final DebtSimplificationService debtSimplificationService;


    public GroupController(GroupService groupService, ExpenseService expenseService, DebtSimplificationService debtSimplificationService) {
        this.groupService = groupService;
        this.expenseService = expenseService;
        this.debtSimplificationService = debtSimplificationService;
    }

    @PostMapping("/createGroup")
    public ResponseEntity<String> createGroup(@RequestBody Group group){
      try{
          this.groupService.createGroup(group);
      } catch (Exception e) {
          throw new RuntimeException(e);
      }

      return ResponseEntity.status(HttpStatus.CREATED).body("Group created " + group.getId());

    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestParam String groupId,
            @RequestBody User user){
        try {
            this.groupService.addUser(groupId,user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body("User " +
                "added to group: " + user.getId());
    }

    @PostMapping("/addExpenses/{groupId}")
    public ResponseEntity<String> addExpenses(@PathVariable String groupId,
                                              @RequestBody Expense expense){
        try {
            this.expenseService.addExpenses(groupId,expense);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Expendse added to groupId: " + groupId);

    }

    @PostMapping("/simplifyExpenses/{groupId}")
    public ResponseEntity<List<String>> simplifyGroupExpenses(@PathVariable String groupId){
        List<String> ansList = new ArrayList<>();
        try{
            ansList =this.debtSimplificationService.simplifyDebtInGroup(groupId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(ansList);

    }


}
