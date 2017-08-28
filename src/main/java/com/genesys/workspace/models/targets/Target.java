package com.genesys.workspace.models.targets;

import com.genesys.workspace.Util;
import com.genesys.workspace.models.AgentState;
import com.genesys.workspace.models.AgentWorkMode;
import com.genesys.workspace.models.targets.availability.*;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class Target {
    private String name;
    private String number;
    private TargetType type;
    private Object availability;

    public Target(com.genesys._internal.workspace.model.Target target) {
        this.name = target.getName();
        this.number = target.getNumber();

        switch (target.getType()) {
            case AGENT:
                this.type = TargetType.AGENT;
                this.extractAgentAvailability(target);
                break;

            case ACD_QUEUE:
                this.type = TargetType.ACD_QUEUE;
                this.extractDNAvailability(target);
                break;

            case AGENT_GROUP:
                this.type = TargetType.AGENT_GROUP;
                this.extractAgentGroupAvailability(target);
                break;

            case ROUTE_POINT:
                this.type = TargetType.ROUTE_POINT;
                this.extractDNAvailability(target);
                break;

            case SKILL:
                this.type = TargetType.SKILL;
                break;

            case CUSTOM_CONTACT:
                this.type = TargetType.CUSTOM_CONTACT;
                break;
        }
    }

    private void extractAgentAvailability(com.genesys._internal.workspace.model.Target target) {
        LinkedTreeMap availabilityData = (LinkedTreeMap)target.getAvailability();
        if (availabilityData == null) {
            return;
        }

        List channelsData = (List)availabilityData.get("channels");
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

        this.availability = new AgentAvailability(channels);
    }

    private void extractAgentGroupAvailability(com.genesys._internal.workspace.model.Target target) {
        LinkedTreeMap availabilityData = (LinkedTreeMap)target.getAvailability();
        if (availabilityData == null) {
            return;
        }

        Integer readyAgents = (Integer)availabilityData.get("readyAgents");
        this.availability = new AgentGroupAvailability(readyAgents);
    }

    private void extractDNAvailability(com.genesys._internal.workspace.model.Target target) {
        LinkedTreeMap availabilityData = (LinkedTreeMap)target.getAvailability();
        if (availabilityData == null) {
            return;
        }

        Integer waitingCalls = (Integer)availabilityData.get("waitingCalls");
        if (this.type == TargetType.ACD_QUEUE) {
            this.availability = new ACDQueueAvailability(waitingCalls);
        } else {
            this.availability = new RoutePointAvailability(waitingCalls);
        }
    }

    public AgentAvailability getAgentAvailability() {
        if (this.type != TargetType.AGENT || this.availability == null) {
            return null;
        } else {
            return (AgentAvailability) this.availability;
        }
    }

    public AgentGroupAvailability getAgentGroupAvailability() {
        if (this.type != TargetType.AGENT_GROUP || this.availability == null) {
            return null;
        } else {
            return (AgentGroupAvailability) this.availability;
        }
    }

    public RoutePointAvailability getRoutePointAvailability() {
        if (this.type != TargetType.ROUTE_POINT || this.availability == null) {
            return null;
        } else {
            return (RoutePointAvailability) this.availability;
        }
    }

    public ACDQueueAvailability getACDQueueAvailability() {
        if (this.type != TargetType.ACD_QUEUE || this.availability == null) {
            return null;
        } else {
            return (ACDQueueAvailability) this.availability;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public TargetType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        String str = "name [" + this.name + "] type [" + this.type + "]";

        if (this.number != null) {
            str += " number [" + this.number + "]\n";
        }

        if (this.availability != null) {
            str += " availability " + this.availability;
        }

        return str;
    }
}

