package com.genesys.workspace.models.cfg;

import java.util.Collection;

public class BusinessAttribute {
    private long dbid;
    private String name;
    private String displayName;
    private String description;
    private Collection<BusinessAttributeValue> values;

    public BusinessAttribute(
            long dbid,
            String name,
            String displayName,
            String description,
            Collection<BusinessAttributeValue> values
    ) {
        this.dbid = dbid;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.values = values;
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

    public Collection<BusinessAttributeValue> getValues() {
        return this.values;
    }

    @Override
    public String toString() {
        String str = "DBID [" + this.dbid + "] name [" + this.name + "] displayName [" + this.displayName + "] description ["
                + this.description + "] values [\n";
        for (BusinessAttributeValue value : this.values) {
            str += "  DBID [" + value.getDBID() + "]name [" + value.getName() + "] displayName [" + value.getDisplayName() + "] description ["
                    + value.getDescription() + "] default [" + value.getDefaultValue() + "]\n";
        }
        str += "]";
        return str;
    }
}
