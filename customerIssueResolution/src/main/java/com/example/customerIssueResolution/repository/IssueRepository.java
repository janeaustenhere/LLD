package com.example.customerIssueResolution.repository;

import com.example.customerIssueResolution.models.Issue;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IssueRepository {

    public final Map<String, Issue> issues = new HashMap<>();

    public void createIssue(Issue issue){
        issues.put(issue.getIssueId(), issue);
    }

    public Issue getIssueById(String issueId){
        return issues.get(issueId);
    }

    public List<Issue> getAllIssues(){
        return issues.values().stream().toList();
    }


}
