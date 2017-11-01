package com.genesys.workspace.models;

public class Dn {
    private String number;
    private String agentId;
    private AgentState agentState;
    private AgentWorkMode workMode;
    private String forwardTo;
    private boolean dnd;

    public Dn() {}

    public String getAgentId() {
        return this.agentId;
    }

    public String getNumber() {
        return this.number;
    }

    public AgentState getAgentState() {
        return this.agentState;
    }

    public AgentWorkMode getWorkMode() {
        return this.workMode;
    }

    public String getForwardTo() {
        return this.forwardTo;
    }

    public boolean isDND() {
        return this.dnd;
    }

    public void setAgentState(AgentState agentState) {
        this.agentState = agentState;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setWorkMode(AgentWorkMode workMode) {
        this.workMode = workMode;
    }

    public void setForwardTo(String forwardTo) {
        this.forwardTo = forwardTo;
    }

    public void setDND(boolean dnd) {
        this.dnd = dnd;
    }
}
