/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.24.2335
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
import com.genesys.internal.workspace.model.Kvpair;
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
 * MediamanagementmanageuserdataData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-10-19T12:59:24.460Z")
public class MediamanagementmanageuserdataData {
  @SerializedName("addedUserData")
  private List<Kvpair> addedUserData = null;

  @SerializedName("updatedUserData")
  private List<Kvpair> updatedUserData = null;

  @SerializedName("deletedUserData")
  private List<String> deletedUserData = null;

  public MediamanagementmanageuserdataData addedUserData(List<Kvpair> addedUserData) {
    this.addedUserData = addedUserData;
    return this;
  }

  public MediamanagementmanageuserdataData addAddedUserDataItem(Kvpair addedUserDataItem) {
    if (this.addedUserData == null) {
      this.addedUserData = new ArrayList<Kvpair>();
    }
    this.addedUserData.add(addedUserDataItem);
    return this;
  }

   /**
   * KVP pairs to add
   * @return addedUserData
  **/
  @ApiModelProperty(value = "KVP pairs to add")
  public List<Kvpair> getAddedUserData() {
    return addedUserData;
  }

  public void setAddedUserData(List<Kvpair> addedUserData) {
    this.addedUserData = addedUserData;
  }

  public MediamanagementmanageuserdataData updatedUserData(List<Kvpair> updatedUserData) {
    this.updatedUserData = updatedUserData;
    return this;
  }

  public MediamanagementmanageuserdataData addUpdatedUserDataItem(Kvpair updatedUserDataItem) {
    if (this.updatedUserData == null) {
      this.updatedUserData = new ArrayList<Kvpair>();
    }
    this.updatedUserData.add(updatedUserDataItem);
    return this;
  }

   /**
   * KVP Pairs to update
   * @return updatedUserData
  **/
  @ApiModelProperty(value = "KVP Pairs to update")
  public List<Kvpair> getUpdatedUserData() {
    return updatedUserData;
  }

  public void setUpdatedUserData(List<Kvpair> updatedUserData) {
    this.updatedUserData = updatedUserData;
  }

  public MediamanagementmanageuserdataData deletedUserData(List<String> deletedUserData) {
    this.deletedUserData = deletedUserData;
    return this;
  }

  public MediamanagementmanageuserdataData addDeletedUserDataItem(String deletedUserDataItem) {
    if (this.deletedUserData == null) {
      this.deletedUserData = new ArrayList<String>();
    }
    this.deletedUserData.add(deletedUserDataItem);
    return this;
  }

   /**
   * Keys to delete
   * @return deletedUserData
  **/
  @ApiModelProperty(value = "Keys to delete")
  public List<String> getDeletedUserData() {
    return deletedUserData;
  }

  public void setDeletedUserData(List<String> deletedUserData) {
    this.deletedUserData = deletedUserData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediamanagementmanageuserdataData mediamanagementmanageuserdataData = (MediamanagementmanageuserdataData) o;
    return Objects.equals(this.addedUserData, mediamanagementmanageuserdataData.addedUserData) &&
        Objects.equals(this.updatedUserData, mediamanagementmanageuserdataData.updatedUserData) &&
        Objects.equals(this.deletedUserData, mediamanagementmanageuserdataData.deletedUserData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addedUserData, updatedUserData, deletedUserData);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediamanagementmanageuserdataData {\n");
    
    sb.append("    addedUserData: ").append(toIndentedString(addedUserData)).append("\n");
    sb.append("    updatedUserData: ").append(toIndentedString(updatedUserData)).append("\n");
    sb.append("    deletedUserData: ").append(toIndentedString(deletedUserData)).append("\n");
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

