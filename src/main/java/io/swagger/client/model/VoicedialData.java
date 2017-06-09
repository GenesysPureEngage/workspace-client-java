/*
 * Workspace Application API
 * Application API used by Workspace Web Edition
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.Kvpair;
import java.util.ArrayList;
import java.util.List;

/**
 * VoicedialData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-06-09T19:46:47.835Z")
public class VoicedialData {
  @SerializedName("destination")
  private String destination = null;

  @SerializedName("location")
  private String location = null;

  @SerializedName("makeCallType")
  private Integer makeCallType = null;

  @SerializedName("userData")
  private List<Kvpair> userData = new ArrayList<Kvpair>();

  @SerializedName("reasons")
  private List<Kvpair> reasons = new ArrayList<Kvpair>();

  @SerializedName("extensions")
  private List<Kvpair> extensions = new ArrayList<Kvpair>();

  @SerializedName("outboundCallerId")
  private String outboundCallerId = null;

  public VoicedialData destination(String destination) {
    this.destination = destination;
    return this;
  }

   /**
   * Directory number of the party the call will be transferred to.
   * @return destination
  **/
  @ApiModelProperty(example = "null", required = true, value = "Directory number of the party the call will be transferred to.")
  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public VoicedialData location(String location) {
    this.location = location;
    return this;
  }

   /**
   * Name of the remote location in the form of <SwitchName> or <T-ServerApplicationName>@<SwitchName>. When there is no need to specify a T-Server for location, this parameter must have the value NULL, not an empty string.
   * @return location
  **/
  @ApiModelProperty(example = "null", value = "Name of the remote location in the form of <SwitchName> or <T-ServerApplicationName>@<SwitchName>. When there is no need to specify a T-Server for location, this parameter must have the value NULL, not an empty string.")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public VoicedialData makeCallType(Integer makeCallType) {
    this.makeCallType = makeCallType;
    return this;
  }

   /**
   * Refer to the type TMakeCallType.
   * @return makeCallType
  **/
  @ApiModelProperty(example = "null", value = "Refer to the type TMakeCallType.")
  public Integer getMakeCallType() {
    return makeCallType;
  }

  public void setMakeCallType(Integer makeCallType) {
    this.makeCallType = makeCallType;
  }

  public VoicedialData userData(List<Kvpair> userData) {
    this.userData = userData;
    return this;
  }

  public VoicedialData addUserDataItem(Kvpair userDataItem) {
    this.userData.add(userDataItem);
    return this;
  }

   /**
   * A key/value pairs list of the user data that should be attached to the call.
   * @return userData
  **/
  @ApiModelProperty(example = "null", value = "A key/value pairs list of the user data that should be attached to the call.")
  public List<Kvpair> getUserData() {
    return userData;
  }

  public void setUserData(List<Kvpair> userData) {
    this.userData = userData;
  }

  public VoicedialData reasons(List<Kvpair> reasons) {
    this.reasons = reasons;
    return this;
  }

  public VoicedialData addReasonsItem(Kvpair reasonsItem) {
    this.reasons.add(reasonsItem);
    return this;
  }

   /**
   * A key/value pairs list of a data structure that provides additional information associated with this action.
   * @return reasons
  **/
  @ApiModelProperty(example = "null", value = "A key/value pairs list of a data structure that provides additional information associated with this action.")
  public List<Kvpair> getReasons() {
    return reasons;
  }

  public void setReasons(List<Kvpair> reasons) {
    this.reasons = reasons;
  }

  public VoicedialData extensions(List<Kvpair> extensions) {
    this.extensions = extensions;
    return this;
  }

  public VoicedialData addExtensionsItem(Kvpair extensionsItem) {
    this.extensions.add(extensionsItem);
    return this;
  }

   /**
   * A key/value pairs list of additional data.
   * @return extensions
  **/
  @ApiModelProperty(example = "null", value = "A key/value pairs list of additional data.")
  public List<Kvpair> getExtensions() {
    return extensions;
  }

  public void setExtensions(List<Kvpair> extensions) {
    this.extensions = extensions;
  }

  public VoicedialData outboundCallerId(String outboundCallerId) {
    this.outboundCallerId = outboundCallerId;
    return this;
  }

   /**
   * value to be set as CPN_DIGITS.
   * @return outboundCallerId
  **/
  @ApiModelProperty(example = "null", value = "value to be set as CPN_DIGITS.")
  public String getOutboundCallerId() {
    return outboundCallerId;
  }

  public void setOutboundCallerId(String outboundCallerId) {
    this.outboundCallerId = outboundCallerId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VoicedialData voicedialData = (VoicedialData) o;
    return Objects.equals(this.destination, voicedialData.destination) &&
        Objects.equals(this.location, voicedialData.location) &&
        Objects.equals(this.makeCallType, voicedialData.makeCallType) &&
        Objects.equals(this.userData, voicedialData.userData) &&
        Objects.equals(this.reasons, voicedialData.reasons) &&
        Objects.equals(this.extensions, voicedialData.extensions) &&
        Objects.equals(this.outboundCallerId, voicedialData.outboundCallerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(destination, location, makeCallType, userData, reasons, extensions, outboundCallerId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VoicedialData {\n");
    
    sb.append("    destination: ").append(toIndentedString(destination)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    makeCallType: ").append(toIndentedString(makeCallType)).append("\n");
    sb.append("    userData: ").append(toIndentedString(userData)).append("\n");
    sb.append("    reasons: ").append(toIndentedString(reasons)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    outboundCallerId: ").append(toIndentedString(outboundCallerId)).append("\n");
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

