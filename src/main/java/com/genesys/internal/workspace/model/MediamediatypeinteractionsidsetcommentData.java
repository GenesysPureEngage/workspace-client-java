/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.78.3880
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
 * MediamediatypeinteractionsidsetcommentData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-03-02T18:41:12.751Z")
public class MediamediatypeinteractionsidsetcommentData {
  @SerializedName("comment")
  private String comment = null;

  public MediamediatypeinteractionsidsetcommentData comment(String comment) {
    this.comment = comment;
    return this;
  }

   /**
   * The comment to add to the interaction.
   * @return comment
  **/
  @ApiModelProperty(required = true, value = "The comment to add to the interaction.")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediamediatypeinteractionsidsetcommentData mediamediatypeinteractionsidsetcommentData = (MediamediatypeinteractionsidsetcommentData) o;
    return Objects.equals(this.comment, mediamediatypeinteractionsidsetcommentData.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comment);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediamediatypeinteractionsidsetcommentData {\n");
    
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
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

