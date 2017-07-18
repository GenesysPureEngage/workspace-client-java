/*
 * Workspace API
 * Application API used by Workspace Web Edition
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.workspace.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ActivatechannelsData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-07-18T12:16:14.468Z")
public class ActivatechannelsData {
  @SerializedName("agentId")
  private String agentId = null;

  @SerializedName("placeName")
  private String placeName = null;

  @SerializedName("dn")
  private String dn = null;

  @SerializedName("queueName")
  private String queueName = null;

  public ActivatechannelsData agentId(String agentId) {
    this.agentId = agentId;
    return this;
  }

   /**
   * agentId (switch login code) that should be used to log the agent in
   * @return agentId
  **/
  @ApiModelProperty(example = "null", value = "agentId (switch login code) that should be used to log the agent in")
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
   * The name of the place that should be used to log the agent in. Either placeName or dn must be provided.
   * @return placeName
  **/
  @ApiModelProperty(example = "null", value = "The name of the place that should be used to log the agent in. Either placeName or dn must be provided.")
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
   * The dn (number) that should be used to login the agent.
   * @return dn
  **/
  @ApiModelProperty(example = "null", value = "The dn (number) that should be used to login the agent.")
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
   * The queue name that should be used to login the agent.
   * @return queueName
  **/
  @ApiModelProperty(example = "null", value = "The queue name that should be used to login the agent.")
  public String getQueueName() {
    return queueName;
  }

  public void setQueueName(String queueName) {
    this.queueName = queueName;
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
        Objects.equals(this.queueName, activatechannelsData.queueName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentId, placeName, dn, queueName);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActivatechannelsData {\n");
    
    sb.append("    agentId: ").append(toIndentedString(agentId)).append("\n");
    sb.append("    placeName: ").append(toIndentedString(placeName)).append("\n");
    sb.append("    dn: ").append(toIndentedString(dn)).append("\n");
    sb.append("    queueName: ").append(toIndentedString(queueName)).append("\n");
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

