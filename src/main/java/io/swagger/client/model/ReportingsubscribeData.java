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
import java.util.ArrayList;
import java.util.List;

/**
 * ReportingsubscribeData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-06-09T19:46:47.835Z")
public class ReportingsubscribeData {
  @SerializedName("socketId")
  private String socketId = null;

  @SerializedName("statistics")
  private List<String> statistics = new ArrayList<String>();

  public ReportingsubscribeData socketId(String socketId) {
    this.socketId = socketId;
    return this;
  }

   /**
   * Get socketId
   * @return socketId
  **/
  @ApiModelProperty(example = "null", required = true, value = "")
  public String getSocketId() {
    return socketId;
  }

  public void setSocketId(String socketId) {
    this.socketId = socketId;
  }

  public ReportingsubscribeData statistics(List<String> statistics) {
    this.statistics = statistics;
    return this;
  }

  public ReportingsubscribeData addStatisticsItem(String statisticsItem) {
    this.statistics.add(statisticsItem);
    return this;
  }

   /**
   * Get statistics
   * @return statistics
  **/
  @ApiModelProperty(example = "null", required = true, value = "")
  public List<String> getStatistics() {
    return statistics;
  }

  public void setStatistics(List<String> statistics) {
    this.statistics = statistics;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportingsubscribeData reportingsubscribeData = (ReportingsubscribeData) o;
    return Objects.equals(this.socketId, reportingsubscribeData.socketId) &&
        Objects.equals(this.statistics, reportingsubscribeData.statistics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(socketId, statistics);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReportingsubscribeData {\n");
    
    sb.append("    socketId: ").append(toIndentedString(socketId)).append("\n");
    sb.append("    statistics: ").append(toIndentedString(statistics)).append("\n");
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

