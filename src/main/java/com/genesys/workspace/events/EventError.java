package com.genesys.workspace.events;

public class EventError {
    private String message;
    private String code;

    public EventError(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }
}
