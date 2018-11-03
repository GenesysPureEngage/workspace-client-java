package com.genesys.workspace.events;

import com.genesys.workspace.models.Call;

public class CallStateChanged {
    private Call call;
    private String previousConnId;
    private NotificationType notificationType;
    public CallStateChanged(Call call, NotificationType notificationType, String previousConnId) {
        this.call = call;
        this.notificationType = notificationType;
        this.previousConnId = previousConnId;
    }

    public Call getCall() {
        return this.call;
    }

    public NotificationType getNotificationType() {
        return this.notificationType;
    }

    public String getPreviousConnId() {
        return this.previousConnId;
    }
}
