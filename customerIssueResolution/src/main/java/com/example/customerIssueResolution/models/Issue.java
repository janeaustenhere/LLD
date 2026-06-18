package com.example.customerIssueResolution.models;

import com.example.customerIssueResolution.enums.IssueStatus;
import com.example.customerIssueResolution.enums.IssueType;
import lombok.Data;

@Data
public class Issue {

    String issueId;
    IssueStatus issueStatus;
    IssueType issueType;
    String description;
    String resolution;
    String assignedAgentID;
    String transactionId;
    String customerEmailId;


}
