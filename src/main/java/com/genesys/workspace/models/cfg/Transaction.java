package com.genesys.workspace.models.cfg;

import java.util.Map;

public class Transaction {
    private String name;
    private String alias;
    private Map<String,Object> userProperties;

    public Transaction(String name, String alias, Map<String,Object> userProperties) {
        this.name = name;
        this.alias = alias;
        this.userProperties = userProperties;
    }

    public Map<String,Object> getUserProperties() {
        return userProperties;
    }

    public String getName() {
        return this.name;
    }

    public String getAlias() {
        return this.alias;
    }

    @Override
    public String toString() {
        return "name [" + this.name + "] alias [" + this.alias + "] userProperties " + this.userProperties;
    }
}
