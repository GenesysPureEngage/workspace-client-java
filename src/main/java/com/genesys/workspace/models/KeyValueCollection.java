package com.genesys.workspace.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        KeyValuePair pair = data.get(key);
        return pair != null? pair.getStringValue(): null;
    }

    @Override
    public Iterator<KeyValuePair> iterator() {
        return this.data.values().iterator();
    }

    public Integer getInt(String key) {
        KeyValuePair pair = data.get(key);
        return pair != null? pair.getIntValue(): null;
    }

    public KeyValueCollection getList(String key) {
        KeyValuePair pair = data.get(key);
        return pair != null? pair.getListValue(): null;
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