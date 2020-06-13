package com.genesys.workspace.events;

public interface ChatMessageEventListener {
    void handleMessageLogUpdated(MessageLogUpdated event);
}
