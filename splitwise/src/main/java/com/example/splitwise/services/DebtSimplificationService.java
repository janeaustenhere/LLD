package com.example.splitwise.services;


import com.example.splitwise.models.BalanceSheet;
import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import com.example.splitwise.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class DebtSimplificationService {

    private final GroupRepository groupRepository;

    public DebtSimplificationService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    record Pair<K,V> (K key, V value){}

    public List<String> simplifyDebtInGroup(String groupId){

        Group group = groupRepository.getGroupDetails(groupId);
        List<Pair<User, BigDecimal>> pairList = new ArrayList<>();
       for(Map.Entry<User,BalanceSheet> map : group.getBalanceSheetMap().entrySet()){
           BalanceSheet balanceSheet = map.getValue();
           BigDecimal paidAmount = balanceSheet.getAmountPaid();
           BigDecimal expenseAmount = balanceSheet.getTotalExpenses();
           BigDecimal diff = expenseAmount.subtract(paidAmount);
           if (diff.compareTo(BigDecimal.ZERO) != 0){
               pairList.add(new Pair<>(map.getKey(),diff));
           }

       }

       pairList.sort(Comparator.comparing(Pair::value));
       List<String> simpifiedList =  new ArrayList<>();

       int start = 0;
       int end = pairList.size() -1;
       while(start < end){



           Pair<User,BigDecimal> pairCreditor = pairList.get(start);
           Pair<User,BigDecimal> pairDebitor = pairList.get(end);

           simpifiedList.add(pairDebitor.key().getUserName() +
                   " has paid amount " + pairDebitor.value().abs().min(pairCreditor.value().abs())
                   + " to " + pairCreditor.key().getUserName());

           BigDecimal diff = pairCreditor.value().abs().subtract(pairDebitor.value());
           if(diff.compareTo(BigDecimal.ZERO) > 0){
               pairList.set(start,new Pair<>(pairCreditor.key(),diff.negate()));
               end--;
           }else if(diff.compareTo(BigDecimal.ZERO) < 0){
               start++;
               pairList.set(end,new Pair<>(pairDebitor.key(),diff.negate()));
           }else {
               start++;
               end--;
           }

       }

       return simpifiedList;



    }
}
