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
 * MediamediatypeinteractionsidrejectData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-03-02T18:41:12.751Z")
public class MediamediatypeinteractionsidrejectData {
  @SerializedName("extension")
  private List<Kvpair> extension = null;

  public MediamediatypeinteractionsidrejectData extension(List<Kvpair> extension) {
    this.extension = extension;
    return this;
  }

  public MediamediatypeinteractionsidrejectData addExtensionItem(Kvpair extensionItem) {
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
    MediamediatypeinteractionsidrejectData mediamediatypeinteractionsidrejectData = (MediamediatypeinteractionsidrejectData) o;
    return Objects.equals(this.extension, mediamediatypeinteractionsidrejectData.extension);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extension);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediamediatypeinteractionsidrejectData {\n");
    
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

