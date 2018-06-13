package com.genesys.workspace.events;

public class EventUserEvent {
    private Map<String, Object> event;

    public EventUserEvent(Map<String, Object> event) {
        this.event = event;
    }

    public Map<String, Object> getEvent(){
        return this.event;
    }
}
