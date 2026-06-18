package com.example.customerIssueResolution.strategy;

import com.example.customerIssueResolution.enums.IssueStatus;
import com.example.customerIssueResolution.models.Agent;
import com.example.customerIssueResolution.models.Issue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultAssignmentStrategy implements AssignmentStrategy {

    @Override
    public Agent assign(List<Agent> agents, Issue issue) {
       Agent assignedAgent =  agents.stream().filter(Agent::isAvailable).
               filter(agent -> agent.getAgentExpertise().contains(issue.getIssueType()))
               .findAny().orElse(null);
     

        if(assignedAgent != null){
            assignedAgent.setAssignedIssueId(issue.getIssueId());
            issue.setIssueStatus(IssueStatus.ASSIGN_TO_AGENT);
            issue.setAssignedAgentID(assignedAgent.getAgentId());
            return assignedAgent;
        }else{
            Agent assignedAgentInWait =  agents.stream(). filter(agent -> agent.getAgentExpertise().contains(issue.getIssueType()))
                    .findAny().orElse(null);
            assignedAgentInWait.setAssignedIssueId(issue.getIssueId());
            issue.setIssueStatus(IssueStatus.ASSIGN_TO_AGENT);
            issue.setAssignedAgentID(assignedAgentInWait.getAgentId());
            return assignedAgentInWait;
        }
    }
}
