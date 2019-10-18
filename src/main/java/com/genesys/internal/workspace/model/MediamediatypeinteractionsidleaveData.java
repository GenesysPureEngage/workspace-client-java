/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.51.3082
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
 * MediamediatypeinteractionsidleaveData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-10-17T21:29:47.364Z")
public class MediamediatypeinteractionsidleaveData {
  /**
   * The action to take after the agent leaves.
   */
  @JsonAdapter(AfterActionEnum.Adapter.class)
  public enum AfterActionEnum {
    CLOSEIFNOAGENTS("CloseIfNoAgents"),
    
    FORCECLOSE("ForceClose"),
    
    KEEPALIVE("KeepAlive");

    private String value;

    AfterActionEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static AfterActionEnum fromValue(String text) {
      for (AfterActionEnum b : AfterActionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<AfterActionEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final AfterActionEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public AfterActionEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return AfterActionEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("afterAction")
  private AfterActionEnum afterAction = null;

  @SerializedName("message")
  private String message = null;

  @SerializedName("messageType")
  private String messageType = null;

  /**
   * Specifies how the message should be treated.
   */
  @JsonAdapter(TreatAsEnum.Adapter.class)
  public enum TreatAsEnum {
    NORMAL("Normal"),
    
    SYSTEM("System");

    private String value;

    TreatAsEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TreatAsEnum fromValue(String text) {
      for (TreatAsEnum b : TreatAsEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<TreatAsEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TreatAsEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TreatAsEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return TreatAsEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("treatAs")
  private TreatAsEnum treatAs = null;

  public MediamediatypeinteractionsidleaveData afterAction(AfterActionEnum afterAction) {
    this.afterAction = afterAction;
    return this;
  }

   /**
   * The action to take after the agent leaves.
   * @return afterAction
  **/
  @ApiModelProperty(value = "The action to take after the agent leaves.")
  public AfterActionEnum getAfterAction() {
    return afterAction;
  }

  public void setAfterAction(AfterActionEnum afterAction) {
    this.afterAction = afterAction;
  }

  public MediamediatypeinteractionsidleaveData message(String message) {
    this.message = message;
    return this;
  }

   /**
   * The message to send to the chat participants.
   * @return message
  **/
  @ApiModelProperty(value = "The message to send to the chat participants.")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public MediamediatypeinteractionsidleaveData messageType(String messageType) {
    this.messageType = messageType;
    return this;
  }

   /**
   * The type of message.
   * @return messageType
  **/
  @ApiModelProperty(value = "The type of message.")
  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public MediamediatypeinteractionsidleaveData treatAs(TreatAsEnum treatAs) {
    this.treatAs = treatAs;
    return this;
  }

   /**
   * Specifies how the message should be treated.
   * @return treatAs
  **/
  @ApiModelProperty(value = "Specifies how the message should be treated.")
  public TreatAsEnum getTreatAs() {
    return treatAs;
  }

  public void setTreatAs(TreatAsEnum treatAs) {
    this.treatAs = treatAs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediamediatypeinteractionsidleaveData mediamediatypeinteractionsidleaveData = (MediamediatypeinteractionsidleaveData) o;
    return Objects.equals(this.afterAction, mediamediatypeinteractionsidleaveData.afterAction) &&
        Objects.equals(this.message, mediamediatypeinteractionsidleaveData.message) &&
        Objects.equals(this.messageType, mediamediatypeinteractionsidleaveData.messageType) &&
        Objects.equals(this.treatAs, mediamediatypeinteractionsidleaveData.treatAs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(afterAction, message, messageType, treatAs);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediamediatypeinteractionsidleaveData {\n");
    
    sb.append("    afterAction: ").append(toIndentedString(afterAction)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    messageType: ").append(toIndentedString(messageType)).append("\n");
    sb.append("    treatAs: ").append(toIndentedString(treatAs)).append("\n");
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

