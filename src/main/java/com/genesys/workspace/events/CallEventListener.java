package com.genesys.workspace.events;

public interface CallEventListener {
    void handleCallStateChanged(CallStateChanged msg);
}


