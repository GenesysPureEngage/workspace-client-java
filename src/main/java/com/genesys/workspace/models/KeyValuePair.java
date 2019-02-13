package com.genesys.workspace.models;

public class KeyValuePair {
    
    public static enum ValueType {
        STRING,
        INT,
        LIST
    }
    
    private String key;
    private Object value;
    private ValueType type;

    public KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
        this.type = ValueType.STRING;
    }

    public KeyValuePair(String key, KeyValueCollection value) {
        this.key = key;
        this.value = value;
        this.type = ValueType.LIST;
    }

    public KeyValuePair(String key, int value) {
        this.key = key;
        this.value = value;
        this.type = ValueType.INT;
    }

    public String getKey() {
        return this.key;
    }

    public ValueType getValueType() {
        return this.type;
    }


    public String getStringValue() {
        return this.type == ValueType.STRING ? (String)this.value : null;
    }

    public Integer getIntValue() {
        return this.type == ValueType.INT ? (Integer)this.value : null;
    }

    public KeyValueCollection getListValue() {
        return this.type == ValueType.LIST ? (KeyValueCollection)this.value : null;
    }

    public Object getValue() {
        return this.value;
    }
}