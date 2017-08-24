package com.genesys.workspace.models.cfg;

public class BusinessAttributeValue {
    private long dbid;
    private String name;
    private String displayName;
    private String description;
    private Object defaultValue;

    public BusinessAttributeValue(
            long dbid,
            String name,
            String displayName,
            String description,
            Object defaultValue
    ) {
        this.dbid = dbid;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.defaultValue = defaultValue;
    }

    public long getDBID() {
        return this.dbid;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getDescription() {
        return this.description;
    }

    public Object getDefaultValue() {
        return this.defaultValue;
    }
}

