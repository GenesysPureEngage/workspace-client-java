/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.31.2540
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

/**
 * FeedbacksubmitData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-02-07T18:47:34.484Z")
public class FeedbacksubmitData {
  @SerializedName("topic")
  private String topic = null;

  @SerializedName("text")
  private String text = null;

  @SerializedName("metadata")
  private String metadata = null;

  @SerializedName("settings")
  private Object settings = null;

  public FeedbacksubmitData topic(String topic) {
    this.topic = topic;
    return this;
  }

   /**
   * topic of feedback
   * @return topic
  **/
  @ApiModelProperty(value = "topic of feedback")
  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public FeedbacksubmitData text(String text) {
    this.text = text;
    return this;
  }

   /**
   * text of feedback
   * @return text
  **/
  @ApiModelProperty(value = "text of feedback")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public FeedbacksubmitData metadata(String metadata) {
    this.metadata = metadata;
    return this;
  }

   /**
   * metadata of feedback
   * @return metadata
  **/
  @ApiModelProperty(value = "metadata of feedback")
  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  public FeedbacksubmitData settings(Object settings) {
    this.settings = settings;
    return this;
  }

   /**
   * setting of the record
   * @return settings
  **/
  @ApiModelProperty(value = "setting of the record")
  public Object getSettings() {
    return settings;
  }

  public void setSettings(Object settings) {
    this.settings = settings;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeedbacksubmitData feedbacksubmitData = (FeedbacksubmitData) o;
    return Objects.equals(this.topic, feedbacksubmitData.topic) &&
        Objects.equals(this.text, feedbacksubmitData.text) &&
        Objects.equals(this.metadata, feedbacksubmitData.metadata) &&
        Objects.equals(this.settings, feedbacksubmitData.settings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topic, text, metadata, settings);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeedbacksubmitData {\n");
    
    sb.append("    topic: ").append(toIndentedString(topic)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    settings: ").append(toIndentedString(settings)).append("\n");
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

