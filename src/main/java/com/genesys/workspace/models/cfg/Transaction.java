package com.genesys.workspace.models.cfg;

import com.genesys.workspace.models.KeyValueCollection;

public class Transaction {
    private String name;
    private String alias;
    private KeyValueCollection userProperties;

    public Transaction(
            String name,
            String alias,
            KeyValueCollection userProperties
    ) {
        this.name = name;
        this.alias = alias;
        this.userProperties = userProperties;
    }

    public String getName() {
        return this.name;
    }

    public String getAlias() {
        return this.alias;
    }

    public KeyValueCollection getUserProperties() {
        return this.userProperties;
    }

    @Override
    public String toString() {
        return "name [" + this.name + "] alias [" + this.alias + "] userProperties " + this.userProperties;
    }
}
