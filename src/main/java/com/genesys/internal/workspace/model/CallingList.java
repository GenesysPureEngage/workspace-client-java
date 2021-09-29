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
import com.genesys.internal.workspace.model.Field;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CallingList
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-09-24T20:41:46.603Z")
public class CallingList {
  @SerializedName("name")
  private String name = null;

  @SerializedName("fields")
  private List<Field> fields = null;

  public CallingList name(String name) {
    this.name = name;
    return this;
  }

   /**
   * name of the calling list
   * @return name
  **/
  @ApiModelProperty(value = "name of the calling list")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CallingList fields(List<Field> fields) {
    this.fields = fields;
    return this;
  }

  public CallingList addFieldsItem(Field fieldsItem) {
    if (this.fields == null) {
      this.fields = new ArrayList<Field>();
    }
    this.fields.add(fieldsItem);
    return this;
  }

   /**
   * Get fields
   * @return fields
  **/
  @ApiModelProperty(value = "")
  public List<Field> getFields() {
    return fields;
  }

  public void setFields(List<Field> fields) {
    this.fields = fields;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CallingList callingList = (CallingList) o;
    return Objects.equals(this.name, callingList.name) &&
        Objects.equals(this.fields, callingList.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, fields);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CallingList {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
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

