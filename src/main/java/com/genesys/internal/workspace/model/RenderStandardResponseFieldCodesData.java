/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.40.2832
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
import com.genesys.internal.workspace.model.UcsresponsesidrenderfieldcodesData;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * RenderStandardResponseFieldCodesData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-04-25T11:28:12.831Z")
public class RenderStandardResponseFieldCodesData {
  @SerializedName("operationId")
  private String operationId = null;

  @SerializedName("data")
  private UcsresponsesidrenderfieldcodesData data = null;

  public RenderStandardResponseFieldCodesData operationId(String operationId) {
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

  public RenderStandardResponseFieldCodesData data(UcsresponsesidrenderfieldcodesData data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(required = true, value = "")
  public UcsresponsesidrenderfieldcodesData getData() {
    return data;
  }

  public void setData(UcsresponsesidrenderfieldcodesData data) {
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
    RenderStandardResponseFieldCodesData renderStandardResponseFieldCodesData = (RenderStandardResponseFieldCodesData) o;
    return Objects.equals(this.operationId, renderStandardResponseFieldCodesData.operationId) &&
        Objects.equals(this.data, renderStandardResponseFieldCodesData.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationId, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RenderStandardResponseFieldCodesData {\n");
    
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

