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
import io.swagger.client.model.CurrentUserDataUserActiveSessionDn;

/**
 * CurrentUserDataUserActiveSession
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-06-09T19:46:47.835Z")
public class CurrentUserDataUserActiveSession {
  @SerializedName("dn")
  private CurrentUserDataUserActiveSessionDn dn = null;

  public CurrentUserDataUserActiveSession dn(CurrentUserDataUserActiveSessionDn dn) {
    this.dn = dn;
    return this;
  }

   /**
   * Get dn
   * @return dn
  **/
  @ApiModelProperty(example = "null", value = "")
  public CurrentUserDataUserActiveSessionDn getDn() {
    return dn;
  }

  public void setDn(CurrentUserDataUserActiveSessionDn dn) {
    this.dn = dn;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CurrentUserDataUserActiveSession currentUserDataUserActiveSession = (CurrentUserDataUserActiveSession) o;
    return Objects.equals(this.dn, currentUserDataUserActiveSession.dn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dn);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CurrentUserDataUserActiveSession {\n");
    
    sb.append("    dn: ").append(toIndentedString(dn)).append("\n");
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

