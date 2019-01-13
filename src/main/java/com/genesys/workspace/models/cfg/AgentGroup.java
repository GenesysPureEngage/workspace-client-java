package com.genesys.workspace.models.cfg;

import com.genesys.workspace.models.KeyValueCollection;

public class AgentGroup {
    private String name;
    private long dbid;
    private KeyValueCollection userProperties;

    public AgentGroup(
            String name,
            long dbid,
            KeyValueCollection userProperties
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

    public KeyValueCollection getUserProperties() {
        return this.userProperties;
    }

    @Override
    public String toString() {
        return "DBID [" + this.dbid + "] name [" + this.name + "] userProperties [" + this.userProperties + "]";
    }
}
