/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.51.3082
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
import java.util.Arrays;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-08-15T18:49:25.968Z")
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
   * The unique identifier for the call.
   * @return id
  **/
  @ApiModelProperty(value = "The unique identifier for the call.")
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
   * The previous connection ID is present if the ID has changed, as would be the case if an agent is the target of a two-step conference or transfer.
   * @return previousConnId
  **/
  @ApiModelProperty(value = "The previous connection ID is present if the ID has changed, as would be the case if an agent is the target of a two-step conference or transfer.")
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
   * The parent connection ID is present on consult calls and identifies the call from which the conference or transfer was initiated.
   * @return parentConnId
  **/
  @ApiModelProperty(value = "The parent connection ID is present on consult calls and identifies the call from which the conference or transfer was initiated.")
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
   * The agent&#39;s phone number.
   * @return phoneNumber
  **/
  @ApiModelProperty(value = "The agent's phone number.")
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
   * The connection ID for the call. This value comes from the Tlib event.
   * @return connId
  **/
  @ApiModelProperty(value = "The connection ID for the call. This value comes from the Tlib event.")
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
   * The universally unique identifier associated with the call. This is a separate identifier that is specifically required by some requests.
   * @return callUuid
  **/
  @ApiModelProperty(value = "The universally unique identifier associated with the call. This is a separate identifier that is specifically required by some requests.")
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
   * The type of call, such as Internal, Inbound, Outbound, Consult.
   * @return callType
  **/
  @ApiModelProperty(value = "The type of call, such as Internal, Inbound, Outbound, Consult.")
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
   * The state of the call, such asRinging, Dialing, Established, Held, Released, or Completed. Unless specifically configured, calls are automatically completed upon release.
   * @return state
  **/
  @ApiModelProperty(value = "The state of the call, such asRinging, Dialing, Established, Held, Released, or Completed. Unless specifically configured, calls are automatically completed upon release.")
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
   * A list of capabilities for the current state. For example, if the current state is Dialing, the list might be [\&quot;HangUp\&quot;, \&quot;Hold\&quot;].
   * @return capabilities
  **/
  @ApiModelProperty(value = "A list of capabilities for the current state. For example, if the current state is Dialing, the list might be [\"HangUp\", \"Hold\"].")
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
   * A list of call participants&amp;mdash;the phone numbers of those currently on the call.
   * @return participants
  **/
  @ApiModelProperty(value = "A list of call participants&mdash;the phone numbers of those currently on the call.")
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
   * The Dialed Number Identification Service from the call.
   * @return dnis
  **/
  @ApiModelProperty(value = "The Dialed Number Identification Service from the call.")
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
   * The Automatic Number Identification from the call.
   * @return ani
  **/
  @ApiModelProperty(value = "The Automatic Number Identification from the call.")
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
   * The call recording state, one of Stopped, Recording, Paused. If the recording was never started for a call this property is absent.
   * @return recordingState
  **/
  @ApiModelProperty(value = "The call recording state, one of Stopped, Recording, Paused. If the recording was never started for a call this property is absent.")
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
   * A key/value pairs list of a data associated with the call.
   * @return userData
  **/
  @ApiModelProperty(value = "A key/value pairs list of a data associated with the call.")
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
   * Media device/hardware reason codes and similar information. For details about extensions, refer to the [*Genesys Events and Models Reference Manual*](https://docs.genesys.com/Documentation/System/Current/GenEM/Extensions).
   * @return extensions
  **/
  @ApiModelProperty(value = "Media device/hardware reason codes and similar information. For details about extensions, refer to the [*Genesys Events and Models Reference Manual*](https://docs.genesys.com/Documentation/System/Current/GenEM/Extensions).")
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

