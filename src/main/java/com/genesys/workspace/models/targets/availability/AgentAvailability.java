package com.genesys.workspace.models.targets.availability;

import java.util.Collection;

public class AgentAvailability extends TargetAvailability {
    private Collection<ChannelAvailability> channels;

    public AgentAvailability(Collection<ChannelAvailability> channels) {
        this.channels = channels;
    }

    public Collection<ChannelAvailability> getChannels() {
        return channels;
    }

    @Override
    public String toString() {
        String str = "channels: ";
        if (this.channels != null && !this.channels.isEmpty()) {
            for(ChannelAvailability channelAvailability : this.channels) {
                str += channelAvailability + " ";
            }
        }
        return str;
    }
}
