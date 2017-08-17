package com.genesys.workspace.models;

public class Call {
    private String id;
    private String state;
    private String parentConnId;
    private String previousConnId;
    private String callType;
    private String[] participants;

    public Call() {}

    public String getId() {
        return this.id;
    }

    public String getState() {
        return this.state;
    }

    public String getParentConnId() { return this.parentConnId; }

    public String getPreviousConnId() {
        return this.previousConnId;
    }

    public String getCallType() {
        return this.callType;
    }

    public String[] getParticipants() {
        return this.participants;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setParentConnId(String parentConnId) {
        this.parentConnId = parentConnId;
    }

    public void setPreviousConnId(String previousConnId) {
        this.previousConnId = previousConnId;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public void setParticipants(String[] participants) {
        this.participants = participants;
    }
}
