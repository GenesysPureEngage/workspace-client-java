/*
 * Workspace API
 * Application API used by Workspace Web Edition
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.workspace.model;

import java.util.Objects;
import com.genesys.workspace.model.Kvpair;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * VoicecallsidsenddtmfData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-07T12:54:53.267Z")
public class VoicecallsidsenddtmfData {
  @SerializedName("dtmfDigits")
  private String dtmfDigits = null;

  @SerializedName("reasons")
  private List<Kvpair> reasons = new ArrayList<Kvpair>();

  @SerializedName("extensions")
  private List<Kvpair> extensions = new ArrayList<Kvpair>();

  public VoicecallsidsenddtmfData dtmfDigits(String dtmfDigits) {
    this.dtmfDigits = dtmfDigits;
    return this;
  }

   /**
   * The digits that should be sent.
   * @return dtmfDigits
  **/
  @ApiModelProperty(example = "null", required = true, value = "The digits that should be sent.")
  public String getDtmfDigits() {
    return dtmfDigits;
  }

  public void setDtmfDigits(String dtmfDigits) {
    this.dtmfDigits = dtmfDigits;
  }

  public VoicecallsidsenddtmfData reasons(List<Kvpair> reasons) {
    this.reasons = reasons;
    return this;
  }

  public VoicecallsidsenddtmfData addReasonsItem(Kvpair reasonsItem) {
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

  public VoicecallsidsenddtmfData extensions(List<Kvpair> extensions) {
    this.extensions = extensions;
    return this;
  }

  public VoicecallsidsenddtmfData addExtensionsItem(Kvpair extensionsItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VoicecallsidsenddtmfData voicecallsidsenddtmfData = (VoicecallsidsenddtmfData) o;
    return Objects.equals(this.dtmfDigits, voicecallsidsenddtmfData.dtmfDigits) &&
        Objects.equals(this.reasons, voicecallsidsenddtmfData.reasons) &&
        Objects.equals(this.extensions, voicecallsidsenddtmfData.extensions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dtmfDigits, reasons, extensions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VoicecallsidsenddtmfData {\n");
    
    sb.append("    dtmfDigits: ").append(toIndentedString(dtmfDigits)).append("\n");
    sb.append("    reasons: ").append(toIndentedString(reasons)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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

