/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.97.4639
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
import java.util.Arrays;
import com.genesys.internal.workspace.model.CallingList;
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
 * CampaignData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-03-03T19:45:16.214Z")
public class CampaignData {
  @SerializedName("name")
  private String name = null;

  @SerializedName("callingLists")
  private List<CallingList> callingLists = null;

  public CampaignData name(String name) {
    this.name = name;
    return this;
  }

   /**
   * name of the campaign
   * @return name
  **/
  @ApiModelProperty(value = "name of the campaign")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CampaignData callingLists(List<CallingList> callingLists) {
    this.callingLists = callingLists;
    return this;
  }

  public CampaignData addCallingListsItem(CallingList callingListsItem) {
    if (this.callingLists == null) {
      this.callingLists = new ArrayList<CallingList>();
    }
    this.callingLists.add(callingListsItem);
    return this;
  }

   /**
   * Get callingLists
   * @return callingLists
  **/
  @ApiModelProperty(value = "")
  public List<CallingList> getCallingLists() {
    return callingLists;
  }

  public void setCallingLists(List<CallingList> callingLists) {
    this.callingLists = callingLists;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CampaignData campaignData = (CampaignData) o;
    return Objects.equals(this.name, campaignData.name) &&
        Objects.equals(this.callingLists, campaignData.callingLists);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, callingLists);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CampaignData {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    callingLists: ").append(toIndentedString(callingLists)).append("\n");
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

