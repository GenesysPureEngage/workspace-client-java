package com.genesys.workspace.models.targets.availability;

import com.genesys.workspace.models.AgentState;
import com.genesys.workspace.models.AgentWorkMode;

public class ChannelAvailability {
    private String channelName;
    private boolean available;
    private AgentState agentState;
    private AgentWorkMode agentWorkMode;
    private String reason;
    private String phoneNumber;
    private String switchName;
    private AgentActivity activity;

    public ChannelAvailability(
            String channelName,
            boolean available,
            AgentState agentState,
            AgentWorkMode agentWorkMode,
            String reason,
            String phoneNumber,
            String switchName,
            AgentActivity activity
    ) {
        this.channelName = channelName;
        this.available = available;
        this.agentState = agentState;
        this.agentWorkMode = agentWorkMode;
        this.reason = reason;
        this.phoneNumber = phoneNumber;
        this.switchName = switchName;
        this.activity = activity;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public AgentState getAgentState() {
        return this.agentState;
    }

    public AgentWorkMode getAgentWorkMode() {
        return this.agentWorkMode;
    }

    public String getReason() {
        return this.reason;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getSwitchName() {
        return this.getSwitchName();
    }

    public AgentActivity getActivity() {
        return this.activity;
    }

    @Override
    public String toString() {
        String str = "channelName [" + this.channelName +
                "] available [" + this.available +
                "] agentState [" + this.agentState +
                "] agentWorkMode [" + this.agentWorkMode +
                "] reason [" + this.reason +
                "] phoneNumber [" + this.phoneNumber +
                "] activity [" + this.activity + "]";
        return str;
    }
}
