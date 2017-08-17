package com.genesys.workspace.events;

public interface ErrorEventListener {
    void handleEventError(EventError msg);
}
