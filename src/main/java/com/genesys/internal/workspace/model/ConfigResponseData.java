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
import com.genesys.internal.workspace.model.ConfigResponseDataActionCodes;
import com.genesys.internal.workspace.model.ConfigResponseDataBusinessAttributes;
import com.genesys.internal.workspace.model.ConfigResponseDataTransactions;
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
 * ConfigResponseData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-03-03T19:45:16.214Z")
public class ConfigResponseData {
  @SerializedName("actionCodes")
  private List<ConfigResponseDataActionCodes> actionCodes = null;

  @SerializedName("transactions")
  private List<ConfigResponseDataTransactions> transactions = null;

  @SerializedName("businessAttributes")
  private List<ConfigResponseDataBusinessAttributes> businessAttributes = null;

  public ConfigResponseData actionCodes(List<ConfigResponseDataActionCodes> actionCodes) {
    this.actionCodes = actionCodes;
    return this;
  }

  public ConfigResponseData addActionCodesItem(ConfigResponseDataActionCodes actionCodesItem) {
    if (this.actionCodes == null) {
      this.actionCodes = new ArrayList<ConfigResponseDataActionCodes>();
    }
    this.actionCodes.add(actionCodesItem);
    return this;
  }

   /**
   * Get actionCodes
   * @return actionCodes
  **/
  @ApiModelProperty(value = "")
  public List<ConfigResponseDataActionCodes> getActionCodes() {
    return actionCodes;
  }

  public void setActionCodes(List<ConfigResponseDataActionCodes> actionCodes) {
    this.actionCodes = actionCodes;
  }

  public ConfigResponseData transactions(List<ConfigResponseDataTransactions> transactions) {
    this.transactions = transactions;
    return this;
  }

  public ConfigResponseData addTransactionsItem(ConfigResponseDataTransactions transactionsItem) {
    if (this.transactions == null) {
      this.transactions = new ArrayList<ConfigResponseDataTransactions>();
    }
    this.transactions.add(transactionsItem);
    return this;
  }

   /**
   * Get transactions
   * @return transactions
  **/
  @ApiModelProperty(value = "")
  public List<ConfigResponseDataTransactions> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<ConfigResponseDataTransactions> transactions) {
    this.transactions = transactions;
  }

  public ConfigResponseData businessAttributes(List<ConfigResponseDataBusinessAttributes> businessAttributes) {
    this.businessAttributes = businessAttributes;
    return this;
  }

  public ConfigResponseData addBusinessAttributesItem(ConfigResponseDataBusinessAttributes businessAttributesItem) {
    if (this.businessAttributes == null) {
      this.businessAttributes = new ArrayList<ConfigResponseDataBusinessAttributes>();
    }
    this.businessAttributes.add(businessAttributesItem);
    return this;
  }

   /**
   * Get businessAttributes
   * @return businessAttributes
  **/
  @ApiModelProperty(value = "")
  public List<ConfigResponseDataBusinessAttributes> getBusinessAttributes() {
    return businessAttributes;
  }

  public void setBusinessAttributes(List<ConfigResponseDataBusinessAttributes> businessAttributes) {
    this.businessAttributes = businessAttributes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigResponseData configResponseData = (ConfigResponseData) o;
    return Objects.equals(this.actionCodes, configResponseData.actionCodes) &&
        Objects.equals(this.transactions, configResponseData.transactions) &&
        Objects.equals(this.businessAttributes, configResponseData.businessAttributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actionCodes, transactions, businessAttributes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigResponseData {\n");
    
    sb.append("    actionCodes: ").append(toIndentedString(actionCodes)).append("\n");
    sb.append("    transactions: ").append(toIndentedString(transactions)).append("\n");
    sb.append("    businessAttributes: ").append(toIndentedString(businessAttributes)).append("\n");
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

