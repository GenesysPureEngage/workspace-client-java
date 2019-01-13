package com.genesys.workspace.models.targets.availability;

public class AgentGroupAvailability extends TargetAvailability {
    int numberOfReadyAgents;
    public AgentGroupAvailability(int numberOfReadyAgents) {
        this.numberOfReadyAgents = numberOfReadyAgents;
    }

    public int getNumberOfReadyAgents() {
        return numberOfReadyAgents;
    }

    @Override
    public String toString() {
        String str = "readyAgents [" + this.numberOfReadyAgents + "]";
        return str;
    }
}
