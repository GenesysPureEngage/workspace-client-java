/*
 * Workspace Application API
 * Application API used by Workspace Web Edition
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VoicenotreadyData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-06-09T19:46:47.835Z")
public class VoicenotreadyData {
  @SerializedName("reasonCode")
  private String reasonCode = null;

  @SerializedName("agentWorkMode")
  private String agentWorkMode = null;

  public VoicenotreadyData reasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
    return this;
  }

   /**
   * the reason code
   * @return reasonCode
  **/
  @ApiModelProperty(example = "null", value = "the reason code")
  public String getReasonCode() {
    return reasonCode;
  }

  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
  }

  public VoicenotreadyData agentWorkMode(String agentWorkMode) {
    this.agentWorkMode = agentWorkMode;
    return this;
  }

   /**
   * the agent workmode.
   * @return agentWorkMode
  **/
  @ApiModelProperty(example = "null", value = "the agent workmode.")
  public String getAgentWorkMode() {
    return agentWorkMode;
  }

  public void setAgentWorkMode(String agentWorkMode) {
    this.agentWorkMode = agentWorkMode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VoicenotreadyData voicenotreadyData = (VoicenotreadyData) o;
    return Objects.equals(this.reasonCode, voicenotreadyData.reasonCode) &&
        Objects.equals(this.agentWorkMode, voicenotreadyData.agentWorkMode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reasonCode, agentWorkMode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VoicenotreadyData {\n");
    
    sb.append("    reasonCode: ").append(toIndentedString(reasonCode)).append("\n");
    sb.append("    agentWorkMode: ").append(toIndentedString(agentWorkMode)).append("\n");
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

