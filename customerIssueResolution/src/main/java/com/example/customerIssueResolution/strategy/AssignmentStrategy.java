package com.example.customerIssueResolution.strategy;

import com.example.customerIssueResolution.models.Agent;
import com.example.customerIssueResolution.models.Issue;

import java.util.List;

public interface AssignmentStrategy {

    Agent assign(List<Agent> agents, Issue issue);
}
