/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 1.0.0
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
import java.util.ArrayList;
import java.util.List;

/**
 * UcslucenesearchData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-01-08T18:04:55.634Z")
public class UcslucenesearchData {
  @SerializedName("maxResults")
  private Integer maxResults = null;

  @SerializedName("query")
  private String query = null;

  @SerializedName("customAttributes")
  private List<String> customAttributes = null;

  public UcslucenesearchData maxResults(Integer maxResults) {
    this.maxResults = maxResults;
    return this;
  }

   /**
   * The maximum number of contacts to be returned
   * @return maxResults
  **/
  @ApiModelProperty(value = "The maximum number of contacts to be returned")
  public Integer getMaxResults() {
    return maxResults;
  }

  public void setMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
  }

  public UcslucenesearchData query(String query) {
    this.query = query;
    return this;
  }

   /**
   * The query to do the lucene search for contacts
   * @return query
  **/
  @ApiModelProperty(required = true, value = "The query to do the lucene search for contacts")
  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public UcslucenesearchData customAttributes(List<String> customAttributes) {
    this.customAttributes = customAttributes;
    return this;
  }

  public UcslucenesearchData addCustomAttributesItem(String customAttributesItem) {
    if (this.customAttributes == null) {
      this.customAttributes = new ArrayList<String>();
    }
    this.customAttributes.add(customAttributesItem);
    return this;
  }

   /**
   * The list of custom contact attributes to be returned for each contact in response
   * @return customAttributes
  **/
  @ApiModelProperty(value = "The list of custom contact attributes to be returned for each contact in response")
  public List<String> getCustomAttributes() {
    return customAttributes;
  }

  public void setCustomAttributes(List<String> customAttributes) {
    this.customAttributes = customAttributes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UcslucenesearchData ucslucenesearchData = (UcslucenesearchData) o;
    return Objects.equals(this.maxResults, ucslucenesearchData.maxResults) &&
        Objects.equals(this.query, ucslucenesearchData.query) &&
        Objects.equals(this.customAttributes, ucslucenesearchData.customAttributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxResults, query, customAttributes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UcslucenesearchData {\n");
    
    sb.append("    maxResults: ").append(toIndentedString(maxResults)).append("\n");
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    customAttributes: ").append(toIndentedString(customAttributes)).append("\n");
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

