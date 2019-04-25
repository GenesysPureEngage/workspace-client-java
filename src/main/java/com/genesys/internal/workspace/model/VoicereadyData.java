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
 * VoicereadyData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-04-25T11:28:12.831Z")
public class VoicereadyData {
  /**
   * The agent workmode.
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

  @SerializedName("reasons")
  private List<Kvpair> reasons = null;

  @SerializedName("extensions")
  private List<Kvpair> extensions = null;

  public VoicereadyData agentWorkMode(AgentWorkModeEnum agentWorkMode) {
    this.agentWorkMode = agentWorkMode;
    return this;
  }

   /**
   * The agent workmode.
   * @return agentWorkMode
  **/
  @ApiModelProperty(value = "The agent workmode.")
  public AgentWorkModeEnum getAgentWorkMode() {
    return agentWorkMode;
  }

  public void setAgentWorkMode(AgentWorkModeEnum agentWorkMode) {
    this.agentWorkMode = agentWorkMode;
  }

  public VoicereadyData reasons(List<Kvpair> reasons) {
    this.reasons = reasons;
    return this;
  }

  public VoicereadyData addReasonsItem(Kvpair reasonsItem) {
    if (this.reasons == null) {
      this.reasons = new ArrayList<Kvpair>();
    }
    this.reasons.add(reasonsItem);
    return this;
  }

   /**
   * Information on causes for, and results of, actions taken by the user of the current DN. For details about reasons, refer to the [*Genesys Events and Models Reference Manual*](https://docs.genesys.com/Documentation/System/Current/GenEM/Reasons).
   * @return reasons
  **/
  @ApiModelProperty(value = "Information on causes for, and results of, actions taken by the user of the current DN. For details about reasons, refer to the [*Genesys Events and Models Reference Manual*](https://docs.genesys.com/Documentation/System/Current/GenEM/Reasons).")
  public List<Kvpair> getReasons() {
    return reasons;
  }

  public void setReasons(List<Kvpair> reasons) {
    this.reasons = reasons;
  }

  public VoicereadyData extensions(List<Kvpair> extensions) {
    this.extensions = extensions;
    return this;
  }

  public VoicereadyData addExtensionsItem(Kvpair extensionsItem) {
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
    VoicereadyData voicereadyData = (VoicereadyData) o;
    return Objects.equals(this.agentWorkMode, voicereadyData.agentWorkMode) &&
        Objects.equals(this.reasons, voicereadyData.reasons) &&
        Objects.equals(this.extensions, voicereadyData.extensions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentWorkMode, reasons, extensions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VoicereadyData {\n");
    
    sb.append("    agentWorkMode: ").append(toIndentedString(agentWorkMode)).append("\n");
    sb.append("    reasons: ").append(toIndentedString(reasons)).append("\n");
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

