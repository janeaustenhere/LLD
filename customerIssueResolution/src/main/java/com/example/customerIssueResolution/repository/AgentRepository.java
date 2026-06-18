package com.example.customerIssueResolution.repository;

import com.example.customerIssueResolution.models.Agent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AgentRepository {

    public final Map<String, Agent> agents = new HashMap<>();

    public void save(Agent agent){
        agents.put(agent.getAgentId(),agent);
    }

    public Agent getAgentById(String agentId){

        return agents.get(agentId);
    }

    public List<Agent> getAllAgents(){
        return agents.values().stream().toList();
    }

}
