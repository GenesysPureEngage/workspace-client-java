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
import com.genesys.internal.workspace.model.CurrentSessionDataUserActiveSession;
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
 * CurrentSessionDataUser
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-01-24T19:05:04.144Z")
public class CurrentSessionDataUser {
  @SerializedName("dbid")
  private Integer dbid = null;

  @SerializedName("firstName")
  private String firstName = null;

  @SerializedName("lastName")
  private String lastName = null;

  @SerializedName("userName")
  private String userName = null;

  @SerializedName("employeeId")
  private String employeeId = null;

  @SerializedName("defaultPlace")
  private String defaultPlace = null;

  @SerializedName("agentLogin")
  private String agentLogin = null;

  @SerializedName("userProperties")
  private List<Kvpair> userProperties = null;

  @SerializedName("activeSession")
  private CurrentSessionDataUserActiveSession activeSession = null;

  public CurrentSessionDataUser dbid(Integer dbid) {
    this.dbid = dbid;
    return this;
  }

   /**
   * Get dbid
   * @return dbid
  **/
  @ApiModelProperty(value = "")
  public Integer getDbid() {
    return dbid;
  }

  public void setDbid(Integer dbid) {
    this.dbid = dbid;
  }

  public CurrentSessionDataUser firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

   /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(value = "")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public CurrentSessionDataUser lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

   /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(value = "")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public CurrentSessionDataUser userName(String userName) {
    this.userName = userName;
    return this;
  }

   /**
   * Get userName
   * @return userName
  **/
  @ApiModelProperty(required = true, value = "")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public CurrentSessionDataUser employeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

   /**
   * Get employeeId
   * @return employeeId
  **/
  @ApiModelProperty(required = true, value = "")
  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public CurrentSessionDataUser defaultPlace(String defaultPlace) {
    this.defaultPlace = defaultPlace;
    return this;
  }

   /**
   * Get defaultPlace
   * @return defaultPlace
  **/
  @ApiModelProperty(value = "")
  public String getDefaultPlace() {
    return defaultPlace;
  }

  public void setDefaultPlace(String defaultPlace) {
    this.defaultPlace = defaultPlace;
  }

  public CurrentSessionDataUser agentLogin(String agentLogin) {
    this.agentLogin = agentLogin;
    return this;
  }

   /**
   * Get agentLogin
   * @return agentLogin
  **/
  @ApiModelProperty(value = "")
  public String getAgentLogin() {
    return agentLogin;
  }

  public void setAgentLogin(String agentLogin) {
    this.agentLogin = agentLogin;
  }

  public CurrentSessionDataUser userProperties(List<Kvpair> userProperties) {
    this.userProperties = userProperties;
    return this;
  }

  public CurrentSessionDataUser addUserPropertiesItem(Kvpair userPropertiesItem) {
    if (this.userProperties == null) {
      this.userProperties = new ArrayList<Kvpair>();
    }
    this.userProperties.add(userPropertiesItem);
    return this;
  }

   /**
   * Get userProperties
   * @return userProperties
  **/
  @ApiModelProperty(value = "")
  public List<Kvpair> getUserProperties() {
    return userProperties;
  }

  public void setUserProperties(List<Kvpair> userProperties) {
    this.userProperties = userProperties;
  }

  public CurrentSessionDataUser activeSession(CurrentSessionDataUserActiveSession activeSession) {
    this.activeSession = activeSession;
    return this;
  }

   /**
   * Get activeSession
   * @return activeSession
  **/
  @ApiModelProperty(value = "")
  public CurrentSessionDataUserActiveSession getActiveSession() {
    return activeSession;
  }

  public void setActiveSession(CurrentSessionDataUserActiveSession activeSession) {
    this.activeSession = activeSession;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CurrentSessionDataUser currentSessionDataUser = (CurrentSessionDataUser) o;
    return Objects.equals(this.dbid, currentSessionDataUser.dbid) &&
        Objects.equals(this.firstName, currentSessionDataUser.firstName) &&
        Objects.equals(this.lastName, currentSessionDataUser.lastName) &&
        Objects.equals(this.userName, currentSessionDataUser.userName) &&
        Objects.equals(this.employeeId, currentSessionDataUser.employeeId) &&
        Objects.equals(this.defaultPlace, currentSessionDataUser.defaultPlace) &&
        Objects.equals(this.agentLogin, currentSessionDataUser.agentLogin) &&
        Objects.equals(this.userProperties, currentSessionDataUser.userProperties) &&
        Objects.equals(this.activeSession, currentSessionDataUser.activeSession);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dbid, firstName, lastName, userName, employeeId, defaultPlace, agentLogin, userProperties, activeSession);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CurrentSessionDataUser {\n");
    
    sb.append("    dbid: ").append(toIndentedString(dbid)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    employeeId: ").append(toIndentedString(employeeId)).append("\n");
    sb.append("    defaultPlace: ").append(toIndentedString(defaultPlace)).append("\n");
    sb.append("    agentLogin: ").append(toIndentedString(agentLogin)).append("\n");
    sb.append("    userProperties: ").append(toIndentedString(userProperties)).append("\n");
    sb.append("    activeSession: ").append(toIndentedString(activeSession)).append("\n");
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

