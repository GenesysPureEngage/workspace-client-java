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
 * MediachatinteractionsidsendcustomnotificationData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-25T17:21:21.923Z")
public class MediachatinteractionsidsendcustomnotificationData {
  @SerializedName("message")
  private String message = null;

  /**
   * Defines which participants in the chat can see the message.
   */
  @JsonAdapter(VisibilityEnum.Adapter.class)
  public enum VisibilityEnum {
    ALL("All"),
    
    AGENT("Agent"),
    
    SUPERVISOR("Supervisor");

    private String value;

    VisibilityEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static VisibilityEnum fromValue(String text) {
      for (VisibilityEnum b : VisibilityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<VisibilityEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final VisibilityEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public VisibilityEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return VisibilityEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("visibility")
  private VisibilityEnum visibility = null;

  @SerializedName("userData")
  private List<Kvpair> userData = null;

  public MediachatinteractionsidsendcustomnotificationData message(String message) {
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

  public MediachatinteractionsidsendcustomnotificationData visibility(VisibilityEnum visibility) {
    this.visibility = visibility;
    return this;
  }

   /**
   * Defines which participants in the chat can see the message.
   * @return visibility
  **/
  @ApiModelProperty(value = "Defines which participants in the chat can see the message.")
  public VisibilityEnum getVisibility() {
    return visibility;
  }

  public void setVisibility(VisibilityEnum visibility) {
    this.visibility = visibility;
  }

  public MediachatinteractionsidsendcustomnotificationData userData(List<Kvpair> userData) {
    this.userData = userData;
    return this;
  }

  public MediachatinteractionsidsendcustomnotificationData addUserDataItem(Kvpair userDataItem) {
    if (this.userData == null) {
      this.userData = new ArrayList<Kvpair>();
    }
    this.userData.add(userDataItem);
    return this;
  }

   /**
   * The data of custom notification. This is an array of objects with the properties key, type, and value.
   * @return userData
  **/
  @ApiModelProperty(value = "The data of custom notification. This is an array of objects with the properties key, type, and value.")
  public List<Kvpair> getUserData() {
    return userData;
  }

  public void setUserData(List<Kvpair> userData) {
    this.userData = userData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediachatinteractionsidsendcustomnotificationData mediachatinteractionsidsendcustomnotificationData = (MediachatinteractionsidsendcustomnotificationData) o;
    return Objects.equals(this.message, mediachatinteractionsidsendcustomnotificationData.message) &&
        Objects.equals(this.visibility, mediachatinteractionsidsendcustomnotificationData.visibility) &&
        Objects.equals(this.userData, mediachatinteractionsidsendcustomnotificationData.userData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, visibility, userData);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediachatinteractionsidsendcustomnotificationData {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    visibility: ").append(toIndentedString(visibility)).append("\n");
    sb.append("    userData: ").append(toIndentedString(userData)).append("\n");
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

