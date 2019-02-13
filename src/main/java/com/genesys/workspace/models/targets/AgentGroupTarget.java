package com.genesys.workspace.models.targets;

import com.genesys.workspace.models.targets.availability.AgentGroupAvailability;
import com.genesys.workspace.models.targets.availability.TargetAvailability;
import com.google.gson.internal.LinkedTreeMap;

public class AgentGroupTarget extends Target {

    public AgentGroupTarget(String name, String number) {
        super(TargetType.AGENT_GROUP, name, number);
    }

    @Override
    protected TargetAvailability extractAvailability(LinkedTreeMap<String, Object> data) {
        Integer readyAgents = ((Double)data.get("readyAgents")).intValue();
        AgentGroupAvailability availability = new AgentGroupAvailability(readyAgents);
        return availability;
    }

    
}
