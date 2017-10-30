package com.genesys.workspace.models.cfg;

import java.util.Map;

public class AgentGroup {
    private String name;
    private long dbid;
    private Map<String,Object> userProperties;

    public AgentGroup(
            String name,
            long dbid,
            Map<String,Object> userProperties
    ) {
        this.name = name;
        this.dbid = dbid;
        this.userProperties = userProperties;
    }

    public String getName() {
        return this.name;
    }

    public long getDBID() {
        return this.dbid;
    }

    public Map<String,Object> getUserProperties() {
        return this.userProperties;
    }

    @Override
    public String toString() {
        return "DBID [" + this.dbid + "] name [" + this.name + "] userProperties [" + this.userProperties + "]";
    }
}
