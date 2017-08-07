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
import com.genesys.workspace.model.ConfigResponseDataValues;
import com.genesys.workspace.model.Kvpair;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * ConfigResponseDataBusinessAttributes
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-07T12:54:53.267Z")
public class ConfigResponseDataBusinessAttributes {
  @SerializedName("name")
  private String name = null;

  @SerializedName("displayName")
  private String displayName = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("values")
  private List<ConfigResponseDataValues> values = new ArrayList<ConfigResponseDataValues>();

  @SerializedName("userProperties")
  private List<Kvpair> userProperties = new ArrayList<Kvpair>();

  public ConfigResponseDataBusinessAttributes name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ConfigResponseDataBusinessAttributes displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

   /**
   * Get displayName
   * @return displayName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public ConfigResponseDataBusinessAttributes description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ConfigResponseDataBusinessAttributes values(List<ConfigResponseDataValues> values) {
    this.values = values;
    return this;
  }

  public ConfigResponseDataBusinessAttributes addValuesItem(ConfigResponseDataValues valuesItem) {
    this.values.add(valuesItem);
    return this;
  }

   /**
   * Get values
   * @return values
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<ConfigResponseDataValues> getValues() {
    return values;
  }

  public void setValues(List<ConfigResponseDataValues> values) {
    this.values = values;
  }

  public ConfigResponseDataBusinessAttributes userProperties(List<Kvpair> userProperties) {
    this.userProperties = userProperties;
    return this;
  }

  public ConfigResponseDataBusinessAttributes addUserPropertiesItem(Kvpair userPropertiesItem) {
    this.userProperties.add(userPropertiesItem);
    return this;
  }

   /**
   * Get userProperties
   * @return userProperties
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<Kvpair> getUserProperties() {
    return userProperties;
  }

  public void setUserProperties(List<Kvpair> userProperties) {
    this.userProperties = userProperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigResponseDataBusinessAttributes configResponseDataBusinessAttributes = (ConfigResponseDataBusinessAttributes) o;
    return Objects.equals(this.name, configResponseDataBusinessAttributes.name) &&
        Objects.equals(this.displayName, configResponseDataBusinessAttributes.displayName) &&
        Objects.equals(this.description, configResponseDataBusinessAttributes.description) &&
        Objects.equals(this.values, configResponseDataBusinessAttributes.values) &&
        Objects.equals(this.userProperties, configResponseDataBusinessAttributes.userProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, displayName, description, values, userProperties);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigResponseDataBusinessAttributes {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
    sb.append("    userProperties: ").append(toIndentedString(userProperties)).append("\n");
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

