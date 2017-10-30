package com.genesys.workspace.models.cfg;

import java.util.Collection;
import java.util.Map;

public class ActionCode {
    private String name;
    private String code;
    private ActionCodeType type;
    private Collection<SubCode> subCodes;
    private Map<String,Object> userProperties;

    public ActionCode(
            String name,
            String code,
            ActionCodeType type,
            Collection<SubCode> subCodes,
            Map<String,Object> userProperties) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.subCodes = subCodes;
        this.userProperties = userProperties;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public ActionCodeType getType() {
        return this.type;
    }

    public Collection<SubCode> getSubCodes() {
        return this.subCodes;
    }

    public Map<String,Object> getUserProperties() {
        return userProperties;
    }
    
    @Override
    public String toString() {
        return "name [" + this.name + "] code [" + this.code + "] userProperties " + this.userProperties;
    }
}
