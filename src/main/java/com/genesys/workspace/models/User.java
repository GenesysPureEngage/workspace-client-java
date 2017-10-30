package com.genesys.workspace.models;

import java.util.Map;

public class User {
    private int dbid;
    private String employeeId;
    private String agentId;
    private String defaultPlace;
    private Map<String,Object> userProperties;

    public User() {}

    public int getDBID() {
        return this.dbid;
    }
    public String getEmployeeId() {
        return this.employeeId;
    }

    public String getAgentId() {
        return this.agentId;
    }

    public String getDefaultPlace() {
        return this.defaultPlace;
    }

    public void setDBID(int dbid) {
        this.dbid = dbid;
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

    public Map<String,Object> getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(Map<String,Object> userProperties) {
        this.userProperties = userProperties;
    }
}
