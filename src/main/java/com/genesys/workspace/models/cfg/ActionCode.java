package com.genesys.workspace.models.cfg;

public class ActionCode {
    private String name;
    private String code;

    public ActionCode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return "name [" + this.name + "] code [" + this.code + "]";
    }
}
