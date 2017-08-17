package com.genesys.workspace.models;

public class User {
    String employeeId;
    String agentId;
    String defaultPlace;

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
}
