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
 * UcsinteractionsidsetcompletedData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-06-14T19:38:46.282Z")
public class UcsinteractionsidsetcompletedData {
  @SerializedName("callDuration")
  private Integer callDuration = null;

  @SerializedName("userData")
  private List<Kvpair> userData = new ArrayList<Kvpair>();

  public UcsinteractionsidsetcompletedData callDuration(Integer callDuration) {
    this.callDuration = callDuration;
    return this;
  }

   /**
   * The duration of the call
   * @return callDuration
  **/
  @ApiModelProperty(required = true, value = "The duration of the call")
  public Integer getCallDuration() {
    return callDuration;
  }

  public void setCallDuration(Integer callDuration) {
    this.callDuration = callDuration;
  }

  public UcsinteractionsidsetcompletedData userData(List<Kvpair> userData) {
    this.userData = userData;
    return this;
  }

  public UcsinteractionsidsetcompletedData addUserDataItem(Kvpair userDataItem) {
    this.userData.add(userDataItem);
    return this;
  }

   /**
   * A key/value pairs list of the user data of the call.
   * @return userData
  **/
  @ApiModelProperty(required = true, value = "A key/value pairs list of the user data of the call.")
  public List<Kvpair> getUserData() {
    return userData;
  }

  public void setUserData(List<Kvpair> userData) {
    this.userData = userData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UcsinteractionsidsetcompletedData ucsinteractionsidsetcompletedData = (UcsinteractionsidsetcompletedData) o;
    return Objects.equals(this.callDuration, ucsinteractionsidsetcompletedData.callDuration) &&
        Objects.equals(this.userData, ucsinteractionsidsetcompletedData.userData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(callDuration, userData);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UcsinteractionsidsetcompletedData {\n");
    
    sb.append("    callDuration: ").append(toIndentedString(callDuration)).append("\n");
    sb.append("    userData: ").append(toIndentedString(userData)).append("\n");
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

