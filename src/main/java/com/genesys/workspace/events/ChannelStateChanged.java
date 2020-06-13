package com.genesys.workspace.events;

import com.genesys.workspace.models.Channel;

import java.util.Map;
import java.util.List;

public class ChannelStateChanged {
    private Map<String, Channel> channels;
    private List<String> channelsUpdated;

    public ChannelStateChanged(Map<String, Channel> channels, List<String> channelsUpdated) {
        this.channels = channels;
        this.channelsUpdated = channelsUpdated;
    }

    public Map<String, Channel> getChannels() {
        return channels;
    }

    public List<String> getChannelsUpdated() {
        return channelsUpdated;
    }
}
