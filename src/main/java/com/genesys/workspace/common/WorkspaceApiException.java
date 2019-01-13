package com.genesys.workspace.common;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonObject;
import com.genesys.internal.common.ApiException;

public class WorkspaceApiException extends Exception {
    public WorkspaceApiException(String msg) {
        super(msg);
    }

    public WorkspaceApiException(String msg, Exception cause) {
        super(msg, cause);
    }

    private ApiException _getApiException() {
        if (this.getCause() == null || !(this.getCause() instanceof ApiException)) {
            return null;
        }

        ApiException apiException = (ApiException)this.getCause();
        return apiException;
    }

    private JsonElement _parseResponseBody() {
        ApiException apiException = this._getApiException();
        if (apiException == null || apiException.getResponseBody() == null) {
            return null;
        } else {
            try {
                JsonElement body = new JsonParser().parse(apiException.getResponseBody());
                return body;
            } catch (Exception x) {
                return null;
            }
        }
    }

    private JsonObject _getStatus() {
        JsonElement bodyElement = this._parseResponseBody();
        if (bodyElement == null) {
            return null;
        }

        JsonObject bodyObject = bodyElement.getAsJsonObject();
        if (bodyObject == null) {
            return null;
        }

        JsonObject statusObject = bodyObject.getAsJsonObject("status");
        return statusObject;
    }

    public Integer getHttpStatusCode() {
        ApiException apiException = this._getApiException();
        if (apiException == null) {
            return null;
        }

        return apiException.getCode();
    }

    public Integer getApiStatusCode() {
        JsonObject status = this._getStatus();
        if (status == null) {
            return null;
        }
        JsonPrimitive primitive = status.getAsJsonPrimitive("code");
        if (primitive != null) {
            return primitive.getAsInt();
        } else {
            return null;
        }

    }

    public String getApiStatusMessage() {
        JsonObject status = this._getStatus();
        if (status == null) {
            return null;
        }
        JsonPrimitive primitive = status.getAsJsonPrimitive("message");
        if (primitive != null) {
            return primitive.getAsString();
        } else {
            return null;
        }
    }
}
