package com.genesys.workspace.models;

public class User {
    private String employeeId;
    private String agentId;
    private String defaultPlace;
    private KeyValueCollection userProperties;

    public User() {}

    public String getEmployeeId() {
        return this.employeeId;
    }

    public String getAgentId() {
        return this.agentId;
    }

    public String getDefaultPlace() {
        return this.defaultPlace;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setDefaultPlace(String defaultPlace) {
        this.defaultPlace = defaultPlace;
    }

    public KeyValueCollection getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(KeyValueCollection userProperties) {
        this.userProperties = userProperties;
    }
}
