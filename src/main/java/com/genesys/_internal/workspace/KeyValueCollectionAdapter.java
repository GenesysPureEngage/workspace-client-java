package com.genesys._internal.workspace;

import java.lang.reflect.Type;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonSerializationContext;

import com.genesys.workspace.models.KeyValuePair;
import com.genesys.workspace.models.KeyValueCollection;

public class KeyValueCollectionAdapter implements JsonSerializer<KeyValueCollection> {

    private JsonElement serializeKeyValuePair(KeyValuePair pair) {
        JsonObject object = new JsonObject();
        object.addProperty("key", pair.getKey());

        switch (pair.getValueType()) {
            case STRING:
                object.addProperty("type", "str");
                object.addProperty("value", pair.getStringValue());
                break;

            case INT:
                object.addProperty("type", "int");
                object.addProperty("value", pair.getIntValue());
                break;

            case LIST:
                object.addProperty("type", "kvlist");
                object.add("value", this.serializeList(pair.getListValue()));
                break;

            default:
                return null;
        }

        return object;
    }

    public JsonElement serializeList(KeyValueCollection list) {
        JsonArray jsonArray = new JsonArray();

        for (KeyValuePair pair : list) {
            JsonElement element = this.serializeKeyValuePair(pair);
            if (element != null) {
                jsonArray.add(element);
            }
        }

        return jsonArray;
    }

    public JsonElement serialize(KeyValueCollection src, Type typeOfSrc, JsonSerializationContext context) {
        return this.serializeList(src);
    }
}
