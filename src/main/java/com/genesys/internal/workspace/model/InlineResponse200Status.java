/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.24.2336
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * InlineResponse200Status
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-10-19T15:16:30.438Z")
public class InlineResponse200Status {
  @SerializedName("code")
  private Integer code = null;

  @SerializedName("message")
  private String message = null;

  public InlineResponse200Status code(Integer code) {
    this.code = code;
    return this;
  }

   /**
   * Indicates whether the operation was successful. &#x60;0&#x60; is a successful synchronous operation; &#x60;1&#x60; is a successful asynchronous operation; and &#x60;2&#x60; is a partially successful synchronous operation. If the value is &#x60;2&#x60; the response includes **data** and **error** objects. All other status codes indicate an error and include a **message** object. See [Getting started](/reference/) for more information about responses and errors.
   * @return code
  **/
  @ApiModelProperty(value = "Indicates whether the operation was successful. `0` is a successful synchronous operation; `1` is a successful asynchronous operation; and `2` is a partially successful synchronous operation. If the value is `2` the response includes **data** and **error** objects. All other status codes indicate an error and include a **message** object. See [Getting started](/reference/) for more information about responses and errors.")
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public InlineResponse200Status message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Provides general information when a request returns an error response. For example, if your request returns a response with error code &#x60;500&#x60; the message might be &#x60;Resource not found.&#x60; See [Getting started](/reference/) for more information about responses and errors.
   * @return message
  **/
  @ApiModelProperty(value = "Provides general information when a request returns an error response. For example, if your request returns a response with error code `500` the message might be `Resource not found.` See [Getting started](/reference/) for more information about responses and errors.")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200Status inlineResponse200Status = (InlineResponse200Status) o;
    return Objects.equals(this.code, inlineResponse200Status.code) &&
        Objects.equals(this.message, inlineResponse200Status.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200Status {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

