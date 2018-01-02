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
import com.genesys.internal.workspace.model.IxnReasonCode;
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
 * WorkbinsworkbinIdaddinteractionData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-01-02T15:04:50.944Z")
public class WorkbinsworkbinIdaddinteractionData {
  @SerializedName("ownerId")
  private String ownerId = null;

  @SerializedName("interactionId")
  private String interactionId = null;

  @SerializedName("reason")
  private IxnReasonCode reason = null;

  @SerializedName("extension")
  private List<Kvpair> extension = null;

  public WorkbinsworkbinIdaddinteractionData ownerId(String ownerId) {
    this.ownerId = ownerId;
    return this;
  }

   /**
   * Id of the owner of the workbin
   * @return ownerId
  **/
  @ApiModelProperty(value = "Id of the owner of the workbin")
  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public WorkbinsworkbinIdaddinteractionData interactionId(String interactionId) {
    this.interactionId = interactionId;
    return this;
  }

   /**
   * Id of the interaction
   * @return interactionId
  **/
  @ApiModelProperty(required = true, value = "Id of the interaction")
  public String getInteractionId() {
    return interactionId;
  }

  public void setInteractionId(String interactionId) {
    this.interactionId = interactionId;
  }

  public WorkbinsworkbinIdaddinteractionData reason(IxnReasonCode reason) {
    this.reason = reason;
    return this;
  }

   /**
   * Get reason
   * @return reason
  **/
  @ApiModelProperty(value = "")
  public IxnReasonCode getReason() {
    return reason;
  }

  public void setReason(IxnReasonCode reason) {
    this.reason = reason;
  }

  public WorkbinsworkbinIdaddinteractionData extension(List<Kvpair> extension) {
    this.extension = extension;
    return this;
  }

  public WorkbinsworkbinIdaddinteractionData addExtensionItem(Kvpair extensionItem) {
    if (this.extension == null) {
      this.extension = new ArrayList<Kvpair>();
    }
    this.extension.add(extensionItem);
    return this;
  }

   /**
   * A key/value pairs list of additional data.
   * @return extension
  **/
  @ApiModelProperty(value = "A key/value pairs list of additional data.")
  public List<Kvpair> getExtension() {
    return extension;
  }

  public void setExtension(List<Kvpair> extension) {
    this.extension = extension;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkbinsworkbinIdaddinteractionData workbinsworkbinIdaddinteractionData = (WorkbinsworkbinIdaddinteractionData) o;
    return Objects.equals(this.ownerId, workbinsworkbinIdaddinteractionData.ownerId) &&
        Objects.equals(this.interactionId, workbinsworkbinIdaddinteractionData.interactionId) &&
        Objects.equals(this.reason, workbinsworkbinIdaddinteractionData.reason) &&
        Objects.equals(this.extension, workbinsworkbinIdaddinteractionData.extension);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ownerId, interactionId, reason, extension);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkbinsworkbinIdaddinteractionData {\n");
    
    sb.append("    ownerId: ").append(toIndentedString(ownerId)).append("\n");
    sb.append("    interactionId: ").append(toIndentedString(interactionId)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    extension: ").append(toIndentedString(extension)).append("\n");
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

