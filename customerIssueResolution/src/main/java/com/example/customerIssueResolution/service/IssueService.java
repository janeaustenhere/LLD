package com.example.customerIssueResolution.service;

import com.example.customerIssueResolution.enums.IssueStatus;
import com.example.customerIssueResolution.models.Agent;
import com.example.customerIssueResolution.models.Issue;
import com.example.customerIssueResolution.repository.AgentRepository;
import com.example.customerIssueResolution.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final AgentRepository agentRepository;

    public IssueService(IssueRepository issueRepository, AgentRepository agentRepository) {
        this.issueRepository = issueRepository;
        this.agentRepository = agentRepository;
    }

    public List<Issue> getIssues(Map<String,String> filter){
       return issueRepository.getAllIssues().stream().filter(issue -> {
            if(filter.containsKey("customerEmailId") &&
                    !issue.getCustomerEmailId().equals(filter.get("customerEmailId"))){
                return false;
            }
           if(filter.containsKey("issueType") &&
                   !issue.getCustomerEmailId().equals(filter.get("issueType"))){
               return false;
           }
           if(filter.containsKey("issueStatus") &&
                   !issue.getCustomerEmailId().equals(filter.get("issueStatus"))){
               return false;
           }
           return true;
       }).toList();
    }

    public Issue createIssue(Issue issue){
        String issueId = UUID.randomUUID().toString();
        issue.setIssueId(issueId);
        issueRepository.createIssue(issue);
        System.out.println("Issue is created. Issue id: " + issueId);
        return issue;

    }

    public Issue updateIssue(String issueId, IssueStatus issueStatus, String resolution) throws Exception {
        Issue issue = issueRepository.getIssueById(issueId);
        if(issue == null){
            throw new Exception("Issue Id is not present");
        }
        issue.setIssueStatus(issueStatus);
        issue.setResolution(resolution);
        System.out.println("Issue is updated. IssueId : "  +issueId);
        return issue;
    }

    public void resolveIssue(String issueId,  String resolution) throws Exception {
        Issue issue = issueRepository.getIssueById(issueId);
        if(issue == null){
            throw new Exception("Issue Id is not present");
        }
        issue.setIssueStatus(IssueStatus.RESOLVED);
        issue.setResolution(resolution);
        if(issue.getAssignedAgentID() != null){
            String agentId = issue.getAssignedAgentID();
            Agent agent = agentRepository.getAgentById(agentId);
            agent.setAssignedIssueId(null);
        }
        System.out.println("Issue is resolved. IssueId::  " + issueId);
    }
}
