package com.example.customerIssueResolution.models;

import com.example.customerIssueResolution.enums.IssueType;
import lombok.Data;

import java.util.*;

@Data
public class Agent {

    String agentId;
    String name;
    String email;
    Set<IssueType> agentExpertise;
    Queue<Issue> waitList = new LinkedList<>();
    List<Issue> history = new ArrayList<>();
    String assignedIssueId;

    public boolean isAvailable(){
        return this.assignedIssueId == null;
    }


}
