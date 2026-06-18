package com.example.customerIssueResolution.controller;

import com.example.customerIssueResolution.models.Issue;
import com.example.customerIssueResolution.repository.IssueRepository;
import com.example.customerIssueResolution.service.IssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/createIssue")
    public ResponseEntity<String> createIssue(@RequestBody Issue issue){

        Issue issueCreated = issueService.createIssue(issue);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(String.format("Issue created %s",issueCreated.getIssueId()));

    }

    @PutMapping("/resolveIssue/{issueId}")
    public ResponseEntity<String> resolveIssue(@PathVariable String issueId, @RequestParam String resolution){

        try {
           issueService.resolveIssue(issueId,resolution);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(String.format("Failed action %s",issueId));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(String.format("Issue Resolved %s",issueId));

    }
}

