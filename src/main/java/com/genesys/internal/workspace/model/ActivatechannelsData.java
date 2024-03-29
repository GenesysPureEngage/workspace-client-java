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
 * ActivatechannelsData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-03-03T19:45:16.214Z")
public class ActivatechannelsData {
  @SerializedName("agentId")
  private String agentId = null;

  @SerializedName("placeName")
  private String placeName = null;

  @SerializedName("dn")
  private String dn = null;

  @SerializedName("queueName")
  private String queueName = null;

  /**
   * The workmode.
   */
  @JsonAdapter(AgentWorkModeEnum.Adapter.class)
  public enum AgentWorkModeEnum {
    AUTOIN("AutoIn"),
    
    MANUALIN("ManualIn");

    private String value;

    AgentWorkModeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static AgentWorkModeEnum fromValue(String text) {
      for (AgentWorkModeEnum b : AgentWorkModeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<AgentWorkModeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final AgentWorkModeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public AgentWorkModeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return AgentWorkModeEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("agentWorkMode")
  private AgentWorkModeEnum agentWorkMode = null;

  @SerializedName("dynamicPhoneNumber")
  private String dynamicPhoneNumber = null;

  @SerializedName("autoCompleteCall")
  private Boolean autoCompleteCall = null;

  @SerializedName("channels")
  private List<String> channels = null;

  public ActivatechannelsData agentId(String agentId) {
    this.agentId = agentId;
    return this;
  }

   /**
   * The unique ID of the agent.
   * @return agentId
  **/
  @ApiModelProperty(value = "The unique ID of the agent.")
  public String getAgentId() {
    return agentId;
  }

  public void setAgentId(String agentId) {
    this.agentId = agentId;
  }

  public ActivatechannelsData placeName(String placeName) {
    this.placeName = placeName;
    return this;
  }

   /**
   * The name of the place to use for the agent. You must provide either the place name or DN.
   * @return placeName
  **/
  @ApiModelProperty(value = "The name of the place to use for the agent. You must provide either the place name or DN.")
  public String getPlaceName() {
    return placeName;
  }

  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }

  public ActivatechannelsData dn(String dn) {
    this.dn = dn;
    return this;
  }

   /**
   * The DN (number) to use for the agent. You must provide either the place name or DN.
   * @return dn
  **/
  @ApiModelProperty(value = "The DN (number) to use for the agent. You must provide either the place name or DN.")
  public String getDn() {
    return dn;
  }

  public void setDn(String dn) {
    this.dn = dn;
  }

  public ActivatechannelsData queueName(String queueName) {
    this.queueName = queueName;
    return this;
  }

   /**
   * The queue name.
   * @return queueName
  **/
  @ApiModelProperty(value = "The queue name.")
  public String getQueueName() {
    return queueName;
  }

  public void setQueueName(String queueName) {
    this.queueName = queueName;
  }

  public ActivatechannelsData agentWorkMode(AgentWorkModeEnum agentWorkMode) {
    this.agentWorkMode = agentWorkMode;
    return this;
  }

   /**
   * The workmode.
   * @return agentWorkMode
  **/
  @ApiModelProperty(value = "The workmode.")
  public AgentWorkModeEnum getAgentWorkMode() {
    return agentWorkMode;
  }

  public void setAgentWorkMode(AgentWorkModeEnum agentWorkMode) {
    this.agentWorkMode = agentWorkMode;
  }

  public ActivatechannelsData dynamicPhoneNumber(String dynamicPhoneNumber) {
    this.dynamicPhoneNumber = dynamicPhoneNumber;
    return this;
  }

   /**
   * An optional dynamic phone number to use for the agent.
   * @return dynamicPhoneNumber
  **/
  @ApiModelProperty(value = "An optional dynamic phone number to use for the agent.")
  public String getDynamicPhoneNumber() {
    return dynamicPhoneNumber;
  }

  public void setDynamicPhoneNumber(String dynamicPhoneNumber) {
    this.dynamicPhoneNumber = dynamicPhoneNumber;
  }

  public ActivatechannelsData autoCompleteCall(Boolean autoCompleteCall) {
    this.autoCompleteCall = autoCompleteCall;
    return this;
  }

   /**
   * Specifies if calls should automatically be completed for the session. Defaults to true if not provided.
   * @return autoCompleteCall
  **/
  @ApiModelProperty(value = "Specifies if calls should automatically be completed for the session. Defaults to true if not provided.")
  public Boolean isAutoCompleteCall() {
    return autoCompleteCall;
  }

  public void setAutoCompleteCall(Boolean autoCompleteCall) {
    this.autoCompleteCall = autoCompleteCall;
  }

  public ActivatechannelsData channels(List<String> channels) {
    this.channels = channels;
    return this;
  }

  public ActivatechannelsData addChannelsItem(String channelsItem) {
    if (this.channels == null) {
      this.channels = new ArrayList<String>();
    }
    this.channels.add(channelsItem);
    return this;
  }

   /**
   * An array of strings that correspond to the channels you want to activate.
   * @return channels
  **/
  @ApiModelProperty(value = "An array of strings that correspond to the channels you want to activate.")
  public List<String> getChannels() {
    return channels;
  }

  public void setChannels(List<String> channels) {
    this.channels = channels;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivatechannelsData activatechannelsData = (ActivatechannelsData) o;
    return Objects.equals(this.agentId, activatechannelsData.agentId) &&
        Objects.equals(this.placeName, activatechannelsData.placeName) &&
        Objects.equals(this.dn, activatechannelsData.dn) &&
        Objects.equals(this.queueName, activatechannelsData.queueName) &&
        Objects.equals(this.agentWorkMode, activatechannelsData.agentWorkMode) &&
        Objects.equals(this.dynamicPhoneNumber, activatechannelsData.dynamicPhoneNumber) &&
        Objects.equals(this.autoCompleteCall, activatechannelsData.autoCompleteCall) &&
        Objects.equals(this.channels, activatechannelsData.channels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentId, placeName, dn, queueName, agentWorkMode, dynamicPhoneNumber, autoCompleteCall, channels);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActivatechannelsData {\n");
    
    sb.append("    agentId: ").append(toIndentedString(agentId)).append("\n");
    sb.append("    placeName: ").append(toIndentedString(placeName)).append("\n");
    sb.append("    dn: ").append(toIndentedString(dn)).append("\n");
    sb.append("    queueName: ").append(toIndentedString(queueName)).append("\n");
    sb.append("    agentWorkMode: ").append(toIndentedString(agentWorkMode)).append("\n");
    sb.append("    dynamicPhoneNumber: ").append(toIndentedString(dynamicPhoneNumber)).append("\n");
    sb.append("    autoCompleteCall: ").append(toIndentedString(autoCompleteCall)).append("\n");
    sb.append("    channels: ").append(toIndentedString(channels)).append("\n");
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

