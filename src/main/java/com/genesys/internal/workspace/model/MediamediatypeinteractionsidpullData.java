/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.43.2934
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
import java.util.Arrays;
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
 * MediamediatypeinteractionsidpullData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-06-13T18:09:38.244Z")
public class MediamediatypeinteractionsidpullData {
  @SerializedName("ownerId")
  private String ownerId = null;

  @SerializedName("workbinId")
  private String workbinId = null;

  @SerializedName("useReviewer")
  private Boolean useReviewer = null;

  @SerializedName("reason")
  private IxnReasonCode reason = null;

  @SerializedName("extension")
  private List<Kvpair> extension = null;

  public MediamediatypeinteractionsidpullData ownerId(String ownerId) {
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

  public MediamediatypeinteractionsidpullData workbinId(String workbinId) {
    this.workbinId = workbinId;
    return this;
  }

   /**
   * Id of the workbin
   * @return workbinId
  **/
  @ApiModelProperty(value = "Id of the workbin")
  public String getWorkbinId() {
    return workbinId;
  }

  public void setWorkbinId(String workbinId) {
    this.workbinId = workbinId;
  }

  public MediamediatypeinteractionsidpullData useReviewer(Boolean useReviewer) {
    this.useReviewer = useReviewer;
    return this;
  }

   /**
   * Indicate the agent is reviewer.
   * @return useReviewer
  **/
  @ApiModelProperty(value = "Indicate the agent is reviewer.")
  public Boolean isUseReviewer() {
    return useReviewer;
  }

  public void setUseReviewer(Boolean useReviewer) {
    this.useReviewer = useReviewer;
  }

  public MediamediatypeinteractionsidpullData reason(IxnReasonCode reason) {
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

  public MediamediatypeinteractionsidpullData extension(List<Kvpair> extension) {
    this.extension = extension;
    return this;
  }

  public MediamediatypeinteractionsidpullData addExtensionItem(Kvpair extensionItem) {
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
    MediamediatypeinteractionsidpullData mediamediatypeinteractionsidpullData = (MediamediatypeinteractionsidpullData) o;
    return Objects.equals(this.ownerId, mediamediatypeinteractionsidpullData.ownerId) &&
        Objects.equals(this.workbinId, mediamediatypeinteractionsidpullData.workbinId) &&
        Objects.equals(this.useReviewer, mediamediatypeinteractionsidpullData.useReviewer) &&
        Objects.equals(this.reason, mediamediatypeinteractionsidpullData.reason) &&
        Objects.equals(this.extension, mediamediatypeinteractionsidpullData.extension);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ownerId, workbinId, useReviewer, reason, extension);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediamediatypeinteractionsidpullData {\n");
    
    sb.append("    ownerId: ").append(toIndentedString(ownerId)).append("\n");
    sb.append("    workbinId: ").append(toIndentedString(workbinId)).append("\n");
    sb.append("    useReviewer: ").append(toIndentedString(useReviewer)).append("\n");
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

