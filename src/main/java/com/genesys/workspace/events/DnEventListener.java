package com.genesys.workspace.events;

public interface DnEventListener {
    void handleDnStateChanged(DnStateChanged message);
}
