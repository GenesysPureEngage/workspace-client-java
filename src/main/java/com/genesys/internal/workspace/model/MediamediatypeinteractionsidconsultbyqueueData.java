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

/**
 * MediamediatypeinteractionsidconsultbyqueueData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-03-03T19:45:16.214Z")
public class MediamediatypeinteractionsidconsultbyqueueData {
  @SerializedName("queue")
  private String queue = null;

  public MediamediatypeinteractionsidconsultbyqueueData queue(String queue) {
    this.queue = queue;
    return this;
  }

   /**
   * The name of the queue where the Workspace API should send the consult invite.
   * @return queue
  **/
  @ApiModelProperty(required = true, value = "The name of the queue where the Workspace API should send the consult invite.")
  public String getQueue() {
    return queue;
  }

  public void setQueue(String queue) {
    this.queue = queue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediamediatypeinteractionsidconsultbyqueueData mediamediatypeinteractionsidconsultbyqueueData = (MediamediatypeinteractionsidconsultbyqueueData) o;
    return Objects.equals(this.queue, mediamediatypeinteractionsidconsultbyqueueData.queue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(queue);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediamediatypeinteractionsidconsultbyqueueData {\n");
    
    sb.append("    queue: ").append(toIndentedString(queue)).append("\n");
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

