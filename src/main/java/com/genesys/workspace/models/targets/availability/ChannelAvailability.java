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
        return channelName;
    }

    public AgentState getAgentState() {
        return agentState;
    }

    public AgentWorkMode getAgentWorkMode() {
        return agentWorkMode;
    }

    public String getReason() {
        return reason;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSwitchName() {
        return switchName;
    }
    
    public AgentActivity getActivity() {
        return this.activity;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        String str = "channelName [" + channelName +
                "] available [" + available +
                "] agentState [" + agentState +
                "] agentWorkMode [" + agentWorkMode +
                "] reason [" + reason +
                "] phoneNumber [" + phoneNumber +
                "] activity [" + activity + "]";
        return str;
    }
}
