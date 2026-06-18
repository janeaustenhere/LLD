package com.example.amazonLocker.strategy;

import com.example.amazonLocker.models.DeliveryAgent;

import java.util.List;

interface AgentAssignmentStrategy {

    DeliveryAgent assignAgent(List<DeliveryAgent> eligibleAgents);
}
