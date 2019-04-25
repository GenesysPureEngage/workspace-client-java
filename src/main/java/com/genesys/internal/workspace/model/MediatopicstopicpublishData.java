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
 * MediatopicstopicpublishData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-04-25T11:28:12.831Z")
public class MediatopicstopicpublishData {
  @SerializedName("eventContent")
  private List<Kvpair> eventContent = new ArrayList<Kvpair>();

  @SerializedName("extension")
  private List<Kvpair> extension = null;

  public MediatopicstopicpublishData eventContent(List<Kvpair> eventContent) {
    this.eventContent = eventContent;
    return this;
  }

  public MediatopicstopicpublishData addEventContentItem(Kvpair eventContentItem) {
    this.eventContent.add(eventContentItem);
    return this;
  }

   /**
   * A collection of key/value pairs.
   * @return eventContent
  **/
  @ApiModelProperty(required = true, value = "A collection of key/value pairs.")
  public List<Kvpair> getEventContent() {
    return eventContent;
  }

  public void setEventContent(List<Kvpair> eventContent) {
    this.eventContent = eventContent;
  }

  public MediatopicstopicpublishData extension(List<Kvpair> extension) {
    this.extension = extension;
    return this;
  }

  public MediatopicstopicpublishData addExtensionItem(Kvpair extensionItem) {
    if (this.extension == null) {
      this.extension = new ArrayList<Kvpair>();
    }
    this.extension.add(extensionItem);
    return this;
  }

   /**
   * A collection of key/value pairs.
   * @return extension
  **/
  @ApiModelProperty(value = "A collection of key/value pairs.")
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
    MediatopicstopicpublishData mediatopicstopicpublishData = (MediatopicstopicpublishData) o;
    return Objects.equals(this.eventContent, mediatopicstopicpublishData.eventContent) &&
        Objects.equals(this.extension, mediatopicstopicpublishData.extension);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventContent, extension);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediatopicstopicpublishData {\n");
    
    sb.append("    eventContent: ").append(toIndentedString(eventContent)).append("\n");
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

