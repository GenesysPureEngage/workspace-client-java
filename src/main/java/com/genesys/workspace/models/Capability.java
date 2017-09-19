package com.genesys.workspace.models;

public enum Capability {
    Answer,
    Release,
    Hold,
    Retrieve,
    SendDtmf,
    AttachUserData,
    DeleteUserDataPair,
    UpdateUserData,
    InitiateConference,
    InitiateTransfer,
    CompleteConference,
    CompleteTransfer,
    SingleStepConference,
    SingleStepTransfer,
    DeleteFromConference,
    StartRecording,
    StopRecording,
    PauseRecording,
    ResumeRecording,
    SwitchToListenIn,
    SwitchToCoaching,
    SwitchToBargeIn,
    Alternate,
    Clear,
    Reconnect,
    Redirect,
    Complete,
    Merge;
    
    public static Capability fromString(String s) {
        s = s.replace("-", "");
        for(Capability v: Capability.values()) {
            if(v.name().compareToIgnoreCase(s) == 0) {
                return v;
            }
        }
        
        return null;
    }
}
