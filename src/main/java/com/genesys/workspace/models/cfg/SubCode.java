package com.genesys.workspace.models.cfg;

public class SubCode {
    private String name;
    private String code;

    public SubCode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }
}
