package com.genesys.workspace.events;


public interface ChannelEventListener {
    void handleChannelStateChanged(ChannelStateChanged event);
}
