package com.genesys.workspace.models;

public class Channel {
  	
  	private AgentState agentState = null;
  	private Boolean dnd = null;
  	
  	public Channel() {}
  	
    public void setAgentState(AgentState agentState) {
        this.agentState = agentState;
    }
    
    public AgentState getAgentState() {
    	return this.agentState;
    }
  	
    public Channel agentState(AgentState agentState) {
    	this.agentState = agentState;
    	return this;
    }
  	
    public void setDnd(Boolean dnd) {
        this.dnd = dnd;
    }
    
    public Boolean getDnd() {
    	return this.dnd;
    }
  	
    public Channel dnd(Boolean dnd) {
    	this.dnd = dnd;
    	return this;
    }
}