package com.genesys.workspace.models;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class KeyValueCollection implements Iterable<KeyValuePair> {
    private Map<String, KeyValuePair> data;

    public KeyValueCollection() {
        this.data = new HashMap<>();
    }

    public void addString(String key, String value) {
        this.data.put(key, new KeyValuePair(key, value));
    }

    public void addInt(String key, int value) {
        this.data.put(key, new KeyValuePair(key, value));
    }

    public void addList(String key, KeyValueCollection value) {
        this.data.put(key, new KeyValuePair(key, value));
    }

    public String getString(String key) {
        KeyValuePair pair = this.data.get(key);
        if (pair == null || pair.getValueType() != ValueType.STRING || pair.getValue() == null) {
            return null;
        }

        return (String)pair.getValue();
    }

    public void getKeyValuePairs() {
        this.data.values().iterator();
    }

    public Iterator<KeyValuePair> iterator() {
        return this.data.values().iterator();
    }

    public Integer getInt(String key) {
        KeyValuePair pair = this.data.get(key);
        if (pair == null || pair.getValueType() != ValueType.INT || pair.getValue() == null) {
            return null;
        }

        return (Integer)pair.getValue();
    }

    public KeyValueCollection getList(String key) {
        KeyValuePair pair = this.data.get(key);
        if (pair == null || pair.getValueType() != ValueType.LIST || pair.getValue() == null) {
            return null;
        }

        return (KeyValueCollection)pair.getValue();
    }

    @Override
    public String toString() {
        String str = "[";
        for(KeyValuePair pair : this.data.values()) {
            switch (pair.getValueType()) {
                case STRING:
                    str += " STRING: " + pair.getKey() + "=" + pair.getStringValue();
                    break;

                case INT:
                    str += " INT: " + pair.getKey() + "=" + pair.getIntValue();
                    break;

                case LIST:
                    str += " LIST: " + pair.getKey() + "=" + pair.getListValue();
                    break;
            }
        }

        str += "]";
        return str;
    }
}
