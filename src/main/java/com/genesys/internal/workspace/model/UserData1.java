/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.78.3880
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
import java.util.Arrays;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsiddeleteuserdataData;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * UserData1
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-07-10T14:04:17.835Z")
public class UserData1 {
  @SerializedName("operationId")
  private String operationId = null;

  @SerializedName("data")
  private MediamediatypeinteractionsiddeleteuserdataData data = null;

  public UserData1 operationId(String operationId) {
    this.operationId = operationId;
    return this;
  }

   /**
   * Get operationId
   * @return operationId
  **/
  @ApiModelProperty(value = "")
  public String getOperationId() {
    return operationId;
  }

  public void setOperationId(String operationId) {
    this.operationId = operationId;
  }

  public UserData1 data(MediamediatypeinteractionsiddeleteuserdataData data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(required = true, value = "")
  public MediamediatypeinteractionsiddeleteuserdataData getData() {
    return data;
  }

  public void setData(MediamediatypeinteractionsiddeleteuserdataData data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserData1 userData1 = (UserData1) o;
    return Objects.equals(this.operationId, userData1.operationId) &&
        Objects.equals(this.data, userData1.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationId, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserData1 {\n");
    
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

