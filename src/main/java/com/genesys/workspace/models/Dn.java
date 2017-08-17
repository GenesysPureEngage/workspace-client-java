package com.genesys.workspace.models;

public class Dn {
    private String number;
    private String agentId;
    private String agentState;
    private String workMode;

    public Dn() {}

    public String getAgentId() {
        return this.agentId;
    }

    public String getNumber() {
        return this.number;
    }

    public String getAgentState() {
        return this.agentState;
    }

    public String getWorkMode() {
        return this.workMode;
    }

    public void setAgentState(String agentState) {
        this.agentState = agentState;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
    }
}
