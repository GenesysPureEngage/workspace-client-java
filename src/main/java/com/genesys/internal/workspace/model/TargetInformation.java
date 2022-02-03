/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 9.0.000.97.4639
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
import java.util.ArrayList;
import java.util.List;

/**
 * The target to save.
 */
@ApiModel(description = "The target to save.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-01-27T00:26:51.593Z")
public class TargetInformation {
  @SerializedName("type")
  private String type = null;

  @SerializedName("id")
  private String id = null;

  @SerializedName("firstName")
  private String firstName = null;

  @SerializedName("lastName")
  private String lastName = null;

  @SerializedName("emailAddresses")
  private List<String> emailAddresses = null;

  @SerializedName("numbers")
  private List<String> numbers = null;

  public TargetInformation type(String type) {
    this.type = type;
    return this;
  }

   /**
   * The type of target.
   * @return type
  **/
  @ApiModelProperty(value = "The type of target.")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public TargetInformation id(String id) {
    this.id = id;
    return this;
  }

   /**
   * The ID of the target.
   * @return id
  **/
  @ApiModelProperty(value = "The ID of the target.")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TargetInformation firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

   /**
   * If the target is an agent, specify the first name.
   * @return firstName
  **/
  @ApiModelProperty(value = "If the target is an agent, specify the first name.")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public TargetInformation lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

   /**
   * If the target is an agent, specify the last name.
   * @return lastName
  **/
  @ApiModelProperty(value = "If the target is an agent, specify the last name.")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public TargetInformation emailAddresses(List<String> emailAddresses) {
    this.emailAddresses = emailAddresses;
    return this;
  }

  public TargetInformation addEmailAddressesItem(String emailAddressesItem) {
    if (this.emailAddresses == null) {
      this.emailAddresses = new ArrayList<String>();
    }
    this.emailAddresses.add(emailAddressesItem);
    return this;
  }

   /**
   * The email addresses to save for this personal favorite.
   * @return emailAddresses
  **/
  @ApiModelProperty(value = "The email addresses to save for this personal favorite.")
  public List<String> getEmailAddresses() {
    return emailAddresses;
  }

  public void setEmailAddresses(List<String> emailAddresses) {
    this.emailAddresses = emailAddresses;
  }

  public TargetInformation numbers(List<String> numbers) {
    this.numbers = numbers;
    return this;
  }

  public TargetInformation addNumbersItem(String numbersItem) {
    if (this.numbers == null) {
      this.numbers = new ArrayList<String>();
    }
    this.numbers.add(numbersItem);
    return this;
  }

   /**
   * The phone numbers to save for this personal favorite.
   * @return numbers
  **/
  @ApiModelProperty(value = "The phone numbers to save for this personal favorite.")
  public List<String> getNumbers() {
    return numbers;
  }

  public void setNumbers(List<String> numbers) {
    this.numbers = numbers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TargetInformation targetInformation = (TargetInformation) o;
    return Objects.equals(this.type, targetInformation.type) &&
        Objects.equals(this.id, targetInformation.id) &&
        Objects.equals(this.firstName, targetInformation.firstName) &&
        Objects.equals(this.lastName, targetInformation.lastName) &&
        Objects.equals(this.emailAddresses, targetInformation.emailAddresses) &&
        Objects.equals(this.numbers, targetInformation.numbers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, firstName, lastName, emailAddresses, numbers);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TargetInformation {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    emailAddresses: ").append(toIndentedString(emailAddresses)).append("\n");
    sb.append("    numbers: ").append(toIndentedString(numbers)).append("\n");
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

