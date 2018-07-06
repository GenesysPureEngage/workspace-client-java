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
 * UcsresponsessearchData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-07-06T17:51:13.982Z")
public class UcsresponsessearchData {
  @SerializedName("query")
  private String query = null;

  @SerializedName("returnedAttributes")
  private List<String> returnedAttributes = null;

  @SerializedName("maxResults")
  private Integer maxResults = null;

  public UcsresponsessearchData query(String query) {
    this.query = query;
    return this;
  }

   /**
   * The query to do the lucene search for standard responses
   * @return query
  **/
  @ApiModelProperty(required = true, value = "The query to do the lucene search for standard responses")
  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public UcsresponsessearchData returnedAttributes(List<String> returnedAttributes) {
    this.returnedAttributes = returnedAttributes;
    return this;
  }

  public UcsresponsessearchData addReturnedAttributesItem(String returnedAttributesItem) {
    if (this.returnedAttributes == null) {
      this.returnedAttributes = new ArrayList<String>();
    }
    this.returnedAttributes.add(returnedAttributesItem);
    return this;
  }

   /**
   * The list of standard responses attributes to be returned for each standard response in request response
   * @return returnedAttributes
  **/
  @ApiModelProperty(value = "The list of standard responses attributes to be returned for each standard response in request response")
  public List<String> getReturnedAttributes() {
    return returnedAttributes;
  }

  public void setReturnedAttributes(List<String> returnedAttributes) {
    this.returnedAttributes = returnedAttributes;
  }

  public UcsresponsessearchData maxResults(Integer maxResults) {
    this.maxResults = maxResults;
    return this;
  }

   /**
   * The maximum number of standard responses to be returned
   * @return maxResults
  **/
  @ApiModelProperty(value = "The maximum number of standard responses to be returned")
  public Integer getMaxResults() {
    return maxResults;
  }

  public void setMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UcsresponsessearchData ucsresponsessearchData = (UcsresponsessearchData) o;
    return Objects.equals(this.query, ucsresponsessearchData.query) &&
        Objects.equals(this.returnedAttributes, ucsresponsessearchData.returnedAttributes) &&
        Objects.equals(this.maxResults, ucsresponsessearchData.maxResults);
  }

  @Override
  public int hashCode() {
    return Objects.hash(query, returnedAttributes, maxResults);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UcsresponsessearchData {\n");
    
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    returnedAttributes: ").append(toIndentedString(returnedAttributes)).append("\n");
    sb.append("    maxResults: ").append(toIndentedString(maxResults)).append("\n");
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

