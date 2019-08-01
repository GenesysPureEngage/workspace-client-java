/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.50.3037
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
import java.util.Arrays;
import com.genesys.internal.workspace.model.ConfigResponseData;
import com.genesys.internal.workspace.model.TargetsResponseStatus;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ConfigResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-08-01T03:30:05.952Z")
public class ConfigResponse {
  @SerializedName("data")
  private ConfigResponseData data = null;

  @SerializedName("status")
  private TargetsResponseStatus status = null;

  public ConfigResponse data(ConfigResponseData data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")
  public ConfigResponseData getData() {
    return data;
  }

  public void setData(ConfigResponseData data) {
    this.data = data;
  }

  public ConfigResponse status(TargetsResponseStatus status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")
  public TargetsResponseStatus getStatus() {
    return status;
  }

  public void setStatus(TargetsResponseStatus status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigResponse configResponse = (ConfigResponse) o;
    return Objects.equals(this.data, configResponse.data) &&
        Objects.equals(this.status, configResponse.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

