package com.example.customerIssueResolution.controller;


import com.example.customerIssueResolution.models.Agent;
import com.example.customerIssueResolution.models.Issue;
import com.example.customerIssueResolution.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/addAgent")
    public ResponseEntity<String> addAgent(@RequestBody Agent agent){
        try {
            agentService.addAgent(agent);

            return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Agent Added %s", agent.getAgentId()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Request failed");
        }
    }

    @GetMapping("/getAllHistory/{agentId}")
    public ResponseEntity<List<Issue>> getAgentAllHistory(@PathVariable String agentId){

        List<Issue> issues = agentService.viewAgentAllHistory(agentId);
        return ResponseEntity.status(HttpStatus.FOUND).body(issues);

    }
}
