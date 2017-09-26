package com.genesys.workspace.models.targets;

import com.genesys.workspace.Util;
import com.genesys.workspace.models.AgentState;
import com.genesys.workspace.models.AgentWorkMode;
import com.genesys.workspace.models.targets.availability.AgentActivity;
import com.genesys.workspace.models.targets.availability.AgentAvailability;
import com.genesys.workspace.models.targets.availability.ChannelAvailability;
import com.genesys.workspace.models.targets.availability.TargetAvailability;
import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;
import java.util.List;

public class AgentTarget extends Target {
    String firstName;
    String lastName;
    String emailAddress;

    public AgentTarget(String name, String number) {
        super(TargetType.AGENT, name, number);
    }

    @Override
    protected TargetAvailability extractAvailability(LinkedTreeMap<String,Object> obj) {
        List channelsData = (List)obj.get("channels");
        List<ChannelAvailability> channels = new ArrayList<>();

        if (channelsData != null && !channelsData.isEmpty()) {
            channelsData.forEach(o -> {
                LinkedTreeMap channelData = (LinkedTreeMap)o;

                String channelName = (String)channelData.get("name");
                boolean available = (Boolean)channelData.get("available");
                LinkedTreeMap userStateData = (LinkedTreeMap)channelData.get("userState");

                AgentState agentState = Util.parseAgentState((String)userStateData.get("state"));
                AgentWorkMode workMode = Util.parseAgentWorkMode((String)userStateData.get("workMode"));
                String reason = (String)userStateData.get("reason");

                String phoneNumber = (String)channelData.get("phoneNumber");
                String switchName = (String)channelData.get("switchName");
                AgentActivity activity = Util.parseAgentActivity((String)channelData.get("activity"));

                channels.add(new ChannelAvailability(channelName, available, agentState, workMode, reason, phoneNumber, switchName, activity));
            });
        }
        
        AgentAvailability availability = new AgentAvailability(channels);
        return availability;                
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
