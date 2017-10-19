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
 * OpenMediaChannel
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T09:04:26.360Z")
public class OpenMediaChannel {
  @SerializedName("name")
  private String name = null;

  @SerializedName("state")
  private String state = null;

  @SerializedName("dnd")
  private Boolean dnd = null;

  @SerializedName("reasons")
  private List<Kvpair> reasons = null;

  @SerializedName("interactions")
  private List<String> interactions = null;

  @SerializedName("capabilities")
  private List<String> capabilities = null;

  public OpenMediaChannel name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public OpenMediaChannel state(String state) {
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(value = "")
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public OpenMediaChannel dnd(Boolean dnd) {
    this.dnd = dnd;
    return this;
  }

   /**
   * Get dnd
   * @return dnd
  **/
  @ApiModelProperty(value = "")
  public Boolean getDnd() {
    return dnd;
  }

  public void setDnd(Boolean dnd) {
    this.dnd = dnd;
  }

  public OpenMediaChannel reasons(List<Kvpair> reasons) {
    this.reasons = reasons;
    return this;
  }

  public OpenMediaChannel addReasonsItem(Kvpair reasonsItem) {
    if (this.reasons == null) {
      this.reasons = new ArrayList<Kvpair>();
    }
    this.reasons.add(reasonsItem);
    return this;
  }

   /**
   * A key/value pairs list of a data structure that provides additional information associated with the state.
   * @return reasons
  **/
  @ApiModelProperty(value = "A key/value pairs list of a data structure that provides additional information associated with the state.")
  public List<Kvpair> getReasons() {
    return reasons;
  }

  public void setReasons(List<Kvpair> reasons) {
    this.reasons = reasons;
  }

  public OpenMediaChannel interactions(List<String> interactions) {
    this.interactions = interactions;
    return this;
  }

  public OpenMediaChannel addInteractionsItem(String interactionsItem) {
    if (this.interactions == null) {
      this.interactions = new ArrayList<String>();
    }
    this.interactions.add(interactionsItem);
    return this;
  }

   /**
   * Get interactions
   * @return interactions
  **/
  @ApiModelProperty(value = "")
  public List<String> getInteractions() {
    return interactions;
  }

  public void setInteractions(List<String> interactions) {
    this.interactions = interactions;
  }

  public OpenMediaChannel capabilities(List<String> capabilities) {
    this.capabilities = capabilities;
    return this;
  }

  public OpenMediaChannel addCapabilitiesItem(String capabilitiesItem) {
    if (this.capabilities == null) {
      this.capabilities = new ArrayList<String>();
    }
    this.capabilities.add(capabilitiesItem);
    return this;
  }

   /**
   * Get capabilities
   * @return capabilities
  **/
  @ApiModelProperty(value = "")
  public List<String> getCapabilities() {
    return capabilities;
  }

  public void setCapabilities(List<String> capabilities) {
    this.capabilities = capabilities;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OpenMediaChannel openMediaChannel = (OpenMediaChannel) o;
    return Objects.equals(this.name, openMediaChannel.name) &&
        Objects.equals(this.state, openMediaChannel.state) &&
        Objects.equals(this.dnd, openMediaChannel.dnd) &&
        Objects.equals(this.reasons, openMediaChannel.reasons) &&
        Objects.equals(this.interactions, openMediaChannel.interactions) &&
        Objects.equals(this.capabilities, openMediaChannel.capabilities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, state, dnd, reasons, interactions, capabilities);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OpenMediaChannel {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    dnd: ").append(toIndentedString(dnd)).append("\n");
    sb.append("    reasons: ").append(toIndentedString(reasons)).append("\n");
    sb.append("    interactions: ").append(toIndentedString(interactions)).append("\n");
    sb.append("    capabilities: ").append(toIndentedString(capabilities)).append("\n");
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

