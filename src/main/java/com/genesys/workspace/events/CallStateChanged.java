package com.genesys.workspace.events;

import com.genesys.workspace.models.Call;

public class CallStateChanged {
    private Call call;
    private String previousConnId;
    public CallStateChanged(Call call, String previousConnId) {
        this.call = call;
        this.previousConnId = previousConnId;
    }

    public Call getCall() {
        return this.call;
    }

    public String getPreviousConnId() {
        return this.previousConnId;
    }
}
