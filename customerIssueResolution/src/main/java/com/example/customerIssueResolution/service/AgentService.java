package com.example.customerIssueResolution.service;


import com.example.customerIssueResolution.models.Agent;
import com.example.customerIssueResolution.models.Issue;
import com.example.customerIssueResolution.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgentService {

    public final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent addAgent(Agent agent){
        String agentId = UUID.randomUUID().toString();
        agent.setAgentId(agentId);
        agentRepository.save(agent);
        System.out.println("Agent added. agentid : " + agentId);
        return agent;
    }

    public List<Issue> viewAgentAllHistory(String agentId){
        Agent agent = agentRepository.getAgentById(agentId);
        return agent.getHistory();
    }
}
