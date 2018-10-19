/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.24.2336
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
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
 * MediamanagementmovetoqueueData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-10-19T15:16:30.438Z")
public class MediamanagementmovetoqueueData {
  @SerializedName("queue")
  private String queue = null;

  @SerializedName("interactions")
  private List<String> interactions = new ArrayList<String>();

  public MediamanagementmovetoqueueData queue(String queue) {
    this.queue = queue;
    return this;
  }

   /**
   * The destination queue.
   * @return queue
  **/
  @ApiModelProperty(required = true, value = "The destination queue.")
  public String getQueue() {
    return queue;
  }

  public void setQueue(String queue) {
    this.queue = queue;
  }

  public MediamanagementmovetoqueueData interactions(List<String> interactions) {
    this.interactions = interactions;
    return this;
  }

  public MediamanagementmovetoqueueData addInteractionsItem(String interactionsItem) {
    this.interactions.add(interactionsItem);
    return this;
  }

   /**
   * List of interactions to move.
   * @return interactions
  **/
  @ApiModelProperty(required = true, value = "List of interactions to move.")
  public List<String> getInteractions() {
    return interactions;
  }

  public void setInteractions(List<String> interactions) {
    this.interactions = interactions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediamanagementmovetoqueueData mediamanagementmovetoqueueData = (MediamanagementmovetoqueueData) o;
    return Objects.equals(this.queue, mediamanagementmovetoqueueData.queue) &&
        Objects.equals(this.interactions, mediamanagementmovetoqueueData.interactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(queue, interactions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediamanagementmovetoqueueData {\n");
    
    sb.append("    queue: ").append(toIndentedString(queue)).append("\n");
    sb.append("    interactions: ").append(toIndentedString(interactions)).append("\n");
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

