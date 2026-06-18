package com.example.customerIssueResolution.service;


import com.example.customerIssueResolution.enums.IssueStatus;
import com.example.customerIssueResolution.models.Agent;
import com.example.customerIssueResolution.models.Issue;
import com.example.customerIssueResolution.repository.AgentRepository;
import com.example.customerIssueResolution.repository.IssueRepository;
import com.example.customerIssueResolution.strategy.AssignmentStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    private final IssueRepository issueRepository;
    private final AgentRepository agentRepository;
    private final AssignmentStrategy assignmentStrategy;


    public AssignmentService(IssueRepository issueRepository, AgentRepository agentRepository, AssignmentStrategy assignmentStrategy) {
        this.issueRepository = issueRepository;
        this.agentRepository = agentRepository;
        this.assignmentStrategy = assignmentStrategy;
    }

    public Agent assignAgent(String issueId){
        Issue issue = issueRepository.getIssueById(issueId);
        List<Agent> agents = agentRepository.getAllAgents();
        return assignmentStrategy.assign(agents,issue);
    }

}
