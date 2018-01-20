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
import com.genesys.internal.workspace.model.CallParticipants;
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
 * Call
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-01-20T21:01:48.082Z")
public class Call {
  @SerializedName("type")
  private String type = null;

  @SerializedName("id")
  private String id = null;

  @SerializedName("previousConnId")
  private String previousConnId = null;

  @SerializedName("parentConnId")
  private String parentConnId = null;

  @SerializedName("phoneNumber")
  private String phoneNumber = null;

  @SerializedName("connId")
  private String connId = null;

  @SerializedName("callUuid")
  private String callUuid = null;

  @SerializedName("callType")
  private String callType = null;

  @SerializedName("state")
  private String state = null;

  @SerializedName("capabilities")
  private List<String> capabilities = null;

  @SerializedName("participants")
  private List<CallParticipants> participants = null;

  @SerializedName("dnis")
  private String dnis = null;

  @SerializedName("ani")
  private String ani = null;

  @SerializedName("recordingState")
  private String recordingState = null;

  @SerializedName("userData")
  private List<Kvpair> userData = null;

  @SerializedName("extensions")
  private List<Kvpair> extensions = null;

  public Call type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Call id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Call previousConnId(String previousConnId) {
    this.previousConnId = previousConnId;
    return this;
  }

   /**
   * Get previousConnId
   * @return previousConnId
  **/
  @ApiModelProperty(value = "")
  public String getPreviousConnId() {
    return previousConnId;
  }

  public void setPreviousConnId(String previousConnId) {
    this.previousConnId = previousConnId;
  }

  public Call parentConnId(String parentConnId) {
    this.parentConnId = parentConnId;
    return this;
  }

   /**
   * Get parentConnId
   * @return parentConnId
  **/
  @ApiModelProperty(value = "")
  public String getParentConnId() {
    return parentConnId;
  }

  public void setParentConnId(String parentConnId) {
    this.parentConnId = parentConnId;
  }

  public Call phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

   /**
   * Get phoneNumber
   * @return phoneNumber
  **/
  @ApiModelProperty(value = "")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Call connId(String connId) {
    this.connId = connId;
    return this;
  }

   /**
   * Get connId
   * @return connId
  **/
  @ApiModelProperty(value = "")
  public String getConnId() {
    return connId;
  }

  public void setConnId(String connId) {
    this.connId = connId;
  }

  public Call callUuid(String callUuid) {
    this.callUuid = callUuid;
    return this;
  }

   /**
   * Get callUuid
   * @return callUuid
  **/
  @ApiModelProperty(value = "")
  public String getCallUuid() {
    return callUuid;
  }

  public void setCallUuid(String callUuid) {
    this.callUuid = callUuid;
  }

  public Call callType(String callType) {
    this.callType = callType;
    return this;
  }

   /**
   * Get callType
   * @return callType
  **/
  @ApiModelProperty(value = "")
  public String getCallType() {
    return callType;
  }

  public void setCallType(String callType) {
    this.callType = callType;
  }

  public Call state(String state) {
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(value = "")
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Call capabilities(List<String> capabilities) {
    this.capabilities = capabilities;
    return this;
  }

  public Call addCapabilitiesItem(String capabilitiesItem) {
    if (this.capabilities == null) {
      this.capabilities = new ArrayList<String>();
    }
    this.capabilities.add(capabilitiesItem);
    return this;
  }

   /**
   * Get capabilities
   * @return capabilities
  **/
  @ApiModelProperty(value = "")
  public List<String> getCapabilities() {
    return capabilities;
  }

  public void setCapabilities(List<String> capabilities) {
    this.capabilities = capabilities;
  }

  public Call participants(List<CallParticipants> participants) {
    this.participants = participants;
    return this;
  }

  public Call addParticipantsItem(CallParticipants participantsItem) {
    if (this.participants == null) {
      this.participants = new ArrayList<CallParticipants>();
    }
    this.participants.add(participantsItem);
    return this;
  }

   /**
   * Get participants
   * @return participants
  **/
  @ApiModelProperty(value = "")
  public List<CallParticipants> getParticipants() {
    return participants;
  }

  public void setParticipants(List<CallParticipants> participants) {
    this.participants = participants;
  }

  public Call dnis(String dnis) {
    this.dnis = dnis;
    return this;
  }

   /**
   * Get dnis
   * @return dnis
  **/
  @ApiModelProperty(value = "")
  public String getDnis() {
    return dnis;
  }

  public void setDnis(String dnis) {
    this.dnis = dnis;
  }

  public Call ani(String ani) {
    this.ani = ani;
    return this;
  }

   /**
   * Get ani
   * @return ani
  **/
  @ApiModelProperty(value = "")
  public String getAni() {
    return ani;
  }

  public void setAni(String ani) {
    this.ani = ani;
  }

  public Call recordingState(String recordingState) {
    this.recordingState = recordingState;
    return this;
  }

   /**
   * Get recordingState
   * @return recordingState
  **/
  @ApiModelProperty(value = "")
  public String getRecordingState() {
    return recordingState;
  }

  public void setRecordingState(String recordingState) {
    this.recordingState = recordingState;
  }

  public Call userData(List<Kvpair> userData) {
    this.userData = userData;
    return this;
  }

  public Call addUserDataItem(Kvpair userDataItem) {
    if (this.userData == null) {
      this.userData = new ArrayList<Kvpair>();
    }
    this.userData.add(userDataItem);
    return this;
  }

   /**
   * A key/value pairs list of a data structure that provides additional information associated with this action.
   * @return userData
  **/
  @ApiModelProperty(value = "A key/value pairs list of a data structure that provides additional information associated with this action.")
  public List<Kvpair> getUserData() {
    return userData;
  }

  public void setUserData(List<Kvpair> userData) {
    this.userData = userData;
  }

  public Call extensions(List<Kvpair> extensions) {
    this.extensions = extensions;
    return this;
  }

  public Call addExtensionsItem(Kvpair extensionsItem) {
    if (this.extensions == null) {
      this.extensions = new ArrayList<Kvpair>();
    }
    this.extensions.add(extensionsItem);
    return this;
  }

   /**
   * A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System).
   * @return extensions
  **/
  @ApiModelProperty(value = "A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System).")
  public List<Kvpair> getExtensions() {
    return extensions;
  }

  public void setExtensions(List<Kvpair> extensions) {
    this.extensions = extensions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Call call = (Call) o;
    return Objects.equals(this.type, call.type) &&
        Objects.equals(this.id, call.id) &&
        Objects.equals(this.previousConnId, call.previousConnId) &&
        Objects.equals(this.parentConnId, call.parentConnId) &&
        Objects.equals(this.phoneNumber, call.phoneNumber) &&
        Objects.equals(this.connId, call.connId) &&
        Objects.equals(this.callUuid, call.callUuid) &&
        Objects.equals(this.callType, call.callType) &&
        Objects.equals(this.state, call.state) &&
        Objects.equals(this.capabilities, call.capabilities) &&
        Objects.equals(this.participants, call.participants) &&
        Objects.equals(this.dnis, call.dnis) &&
        Objects.equals(this.ani, call.ani) &&
        Objects.equals(this.recordingState, call.recordingState) &&
        Objects.equals(this.userData, call.userData) &&
        Objects.equals(this.extensions, call.extensions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, previousConnId, parentConnId, phoneNumber, connId, callUuid, callType, state, capabilities, participants, dnis, ani, recordingState, userData, extensions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Call {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    previousConnId: ").append(toIndentedString(previousConnId)).append("\n");
    sb.append("    parentConnId: ").append(toIndentedString(parentConnId)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    connId: ").append(toIndentedString(connId)).append("\n");
    sb.append("    callUuid: ").append(toIndentedString(callUuid)).append("\n");
    sb.append("    callType: ").append(toIndentedString(callType)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    capabilities: ").append(toIndentedString(capabilities)).append("\n");
    sb.append("    participants: ").append(toIndentedString(participants)).append("\n");
    sb.append("    dnis: ").append(toIndentedString(dnis)).append("\n");
    sb.append("    ani: ").append(toIndentedString(ani)).append("\n");
    sb.append("    recordingState: ").append(toIndentedString(recordingState)).append("\n");
    sb.append("    userData: ").append(toIndentedString(userData)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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

