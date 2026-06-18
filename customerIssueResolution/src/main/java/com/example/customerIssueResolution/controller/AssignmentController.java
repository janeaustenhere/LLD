package com.example.customerIssueResolution.controller;

import com.example.customerIssueResolution.models.Agent;
import com.example.customerIssueResolution.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;


    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/assign/{issueId}")
    public ResponseEntity<String> assignIssue(@PathVariable String issueId){
        Agent agent = assignmentService.assignAgent(issueId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(String.format("Issue assigned to agent: %s ", agent.getAgentId()));
    }
}
